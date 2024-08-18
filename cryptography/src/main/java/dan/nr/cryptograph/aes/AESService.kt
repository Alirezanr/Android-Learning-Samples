package dan.nr.cryptograph.aes

import java.io.File
import javax.crypto.SecretKey

interface AESService {

    fun generateSecretKey(keyLength: Int): SecretKey
    fun convertSecretKeyToString(secretKey: SecretKey): String
    fun convertStringToSecretKey(secretKeyString: String): SecretKey

    suspend fun encrypt(message: String, secretKey: SecretKey): String?
    suspend fun decrypt(message: String, secretKey: SecretKey): String?

    suspend fun encryptFile(inputFile: File, outputFile: File, key: SecretKey): File?
    suspend fun decryptFile(encryptedFile: File, outputFile: File, key: SecretKey): File?

}
