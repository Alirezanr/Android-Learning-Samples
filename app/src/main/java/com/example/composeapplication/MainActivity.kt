package com.example.composeapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import dan.nr.cryptograph.CryptoSession
import dan.nr.cryptograph.CryptoSessionImpl
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val session: CryptoSession = CryptoSessionImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val key = session.getAESService().generateSecretKey(128)
        Log.d("key", key.toString())
        lifecycleScope.launch {
            val textToEncrypt = "Hello World!"
            val encryptedText = session.getAESService().encrypt(textToEncrypt, key)
            Log.d("encryptedText", encryptedText.toString())
            if (encryptedText != null) {
                val decryptedText = session.getAESService().decrypt(encryptedText!!, key)
                Log.d("decryptedText", decryptedText.toString())
            }
        }
    }
}
