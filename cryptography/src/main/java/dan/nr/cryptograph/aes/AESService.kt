package dan.nr.cryptograph.aes

import javax.crypto.SecretKey

interface AESService {

    fun generateSecretKey(keyLength: Int): SecretKey
    fun convertSecretKeyToString(secretKey: SecretKey): String
    fun convertStringToSecretKey(secretKeyString: String): SecretKey

    suspend fun encrypt(message: String, secretKey: SecretKey): String?
    suspend fun decrypt(message: String, secretKey: SecretKey): String?

}
