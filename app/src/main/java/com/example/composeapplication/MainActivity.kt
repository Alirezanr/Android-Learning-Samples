package com.example.composeapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                DrawerMenuItem(
                                    id = "home",
                                    title = "Home",
                                    icon = Icons.Default.Home
                                ),
                                DrawerMenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    icon = Icons.Default.Settings
                                ),
                                DrawerMenuItem(
                                    id = "help",
                                    title = "Get help",
                                    icon = Icons.Default.Info
                                )
                            ), onItemClick = {
                                Toast.makeText(
                                    this@MainActivity,
                                    "${it.title} selected",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    }
                ) {

                }
            }
        }
    }
}

