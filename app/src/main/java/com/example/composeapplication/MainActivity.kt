package com.example.composeapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import dan.nr.cryptograph.CryptoSession
import dan.nr.cryptograph.CryptoSessionImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

class MainActivity : ComponentActivity() {

    val TAG = "MainActivity"

    val session: CryptoSession = CryptoSessionImpl()
    val aesService = session.getAESService()
    val key = aesService.generateSecretKey(128)
    private var filePickerLauncher: ActivityResultLauncher<String>? = null

    override fun onStart() {
        super.onStart()
        fileEncryption()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Button(
                        onClick = {
                            filePickerLauncher?.launch("*/*")
                        }
                    ) {
                        Text(text = "Open file to encrypt and decrypt")
                    }
                }
            }
        }
    }

    private fun fileEncryption() {
        filePickerLauncher =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if (uri != null) {
                    val fileName =
                        FileHelper.getFileName(contentResolver, uri).replace(" ", "")

                    // Split 'fileName' into parts (name, extension) and remove trailing empty strings.
                    val split: Array<String> =
                        fileName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()

                    val fileToEncrypt = FileHelper.createCacheFileFromUri(
                        applicationContext,
                        uri,
                        split[0],
                        "." + split[1]
                    )

                    val pathToEncrypt = osDownloadDirectory() + fileName + ".enc"
                    val encryptedFile = File(pathToEncrypt)
                    fileToEncrypt?.let {
                        CoroutineScope(Dispatchers.Main).launch {
                            aesService.encryptFile(it, encryptedFile, key)?.let { encrypted ->

                                val decryptedOutput = osDownloadDirectory() + "decrypted-${
                                    UUID.randomUUID().toString().substring(0, 4)
                                }" + fileName
                                val decryptedOutputFile = File(decryptedOutput)

                                aesService.decryptFile(
                                    encrypted,
                                    decryptedOutputFile,
                                    key
                                )?.let {
                                    Log.d(TAG, "onStart2: ${it.path}")
                                }
                            }
                        }
                    }
                }
            }
    }

    private fun textEncryption() {
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
