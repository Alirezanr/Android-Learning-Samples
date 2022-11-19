package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.composeapplication.bottom_nav.CustomBottomNavigation
import com.example.composeapplication.bottom_nav.NavigationGraph
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { CustomBottomNavigation(navController = navController) }
                ) {
                    Modifier.padding(it)
                    NavigationGraph(navController = navController)
                }
            }
        }
    }
}
