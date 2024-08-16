package dan.nr.cryptograph

import dan.nr.cryptograph.aes.AESService
import dan.nr.cryptograph.aes.AESServiceImpl

class CryptoSessionImpl : CryptoSession {

    override fun getAESService(): AESService {
        return AESServiceImpl()
    }
}