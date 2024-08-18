package dan.nr.cryptograph.aes

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.security.Security
import java.util.Arrays
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESServiceImpl : AESService {
    private val symmetricAlgorithm = "AES/CBC/PKCS5PADDING"
    private val keyGenerator = KeyGenerator.getInstance("AES")
    private val cipher = Cipher.getInstance(symmetricAlgorithm)
    private val ivSize = 16

    override fun generateSecretKey(
        keyLength: Int
    ): SecretKey {
        keyGenerator.init(keyLength)
        return keyGenerator.generateKey()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun convertSecretKeyToString(secretKey: SecretKey): String {
        return Base64.getEncoder().encodeToString(secretKey.encoded)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun convertStringToSecretKey(secretKeyString: String): SecretKey {
        val decodedBytes = Base64.getDecoder().decode(secretKeyString)
        return SecretKeySpec(decodedBytes, symmetricAlgorithm)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun encrypt(message: String, secretKey: SecretKey): String? =
        withContext(Dispatchers.Default) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            val encryptedBytes = try {
                cipher.doFinal(message.toByteArray())
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }

            val iv = cipher.iv
            val combinedIvAndEncryptedData = iv + encryptedBytes
            return@withContext Base64.getEncoder().encodeToString(combinedIvAndEncryptedData)
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun decrypt(message: String, secretKey: SecretKey): String? =
        withContext(Dispatchers.Default) {
            val decodedBytes = Base64.getDecoder().decode(message)
            val iv = Arrays.copyOfRange(decodedBytes, 0, ivSize)
            val encryptedBytes = Arrays.copyOfRange(decodedBytes, ivSize, decodedBytes.size)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
            val decryptedText = try {
                String(cipher.doFinal(encryptedBytes))
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
            return@withContext decryptedText
        }

    override suspend fun encryptFile(inputFile: File, outputFile: File, key: SecretKey): File? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Security.addProvider(BouncyCastleProvider())
                cipher.init(Cipher.ENCRYPT_MODE, key)
                val iv = cipher.iv
                val inputStream = FileInputStream(inputFile)
                val outputStream = FileOutputStream(outputFile)
                outputStream.write(iv)
                val cipherOutputStream = CipherOutputStream(outputStream, cipher)
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    cipherOutputStream.write(buffer, 0, bytesRead)
                }
                cipherOutputStream.close()
                outputStream.close()
                inputStream.close()

                outputFile
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

    override suspend fun decryptFile(encryptedFile: File, outputFile: File, key: SecretKey): File? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Security.addProvider(BouncyCastleProvider())
                val encryptedDataInputStream = FileInputStream(encryptedFile)
                val iv = ByteArray(ivSize)
                encryptedDataInputStream.read(iv, 0, ivSize)
                cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))
                val outputStream = FileOutputStream(outputFile)
                val cipherInputStream = CipherInputStream(encryptedDataInputStream, cipher)
                val buffer = ByteArray(1024)
                var bytesRead: Int
                while (cipherInputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }

                cipherInputStream.close()
                outputStream.close()
                encryptedDataInputStream.close()
                
                outputFile
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
}