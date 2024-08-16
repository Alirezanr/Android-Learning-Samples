package dan.nr.cryptograph

import dan.nr.cryptograph.aes.AESService

interface CryptoSession {

    fun getAESService(): AESService
}