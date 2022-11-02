package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        val modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(bottom = 14.dp)

                        var isVisible by remember {
                            mutableStateOf(false)
                        }

                        Button(onClick = {
                            isVisible = !isVisible
                        }) {
                            Text(text = "Toggle")
                        }

                        AnimateVisibility(isVisible, modifier)

                    }

                }
            }
        }
    }
}

@Composable
fun AnimateVisibility(
    isVisible: Boolean,
    modifier: Modifier
) {
    //Wrapper composable to animate the visibility changes for our composable.
    AnimatedVisibility(
        visible = isVisible,
        //The animation when composable becomes visible:
        enter = slideInHorizontally() + fadeIn(),
        //The animation when composable becomes invisible:
        exit = slideOutHorizontally() + fadeOut(),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .background(Color.Red)
        )
    }
}
