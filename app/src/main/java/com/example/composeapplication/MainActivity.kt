package com.example.composeapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.rememberNavController
import com.example.composeapplication.bottom_nav.CustomBottomNavigation
import com.example.composeapplication.bottom_nav.NavigationGraph
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { CustomBottomNavigation(navController = navController) },
                    backgroundColor = colorResource(id = R.color.teal_200)
                ) {

                    NavigationGraph(navController = navController)
                }
            }
        }
    }
}
