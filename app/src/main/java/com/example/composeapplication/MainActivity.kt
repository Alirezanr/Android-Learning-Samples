package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                        var isVisible by remember {
                            mutableStateOf(false)
                        }

                        var isRound by remember {
                            mutableStateOf(false)
                        }

                        Button(onClick = {
                            isVisible = !isVisible
                            isRound = !isRound
                        }) {
                            Text(text = "Toggle")
                        }

                        AnimateVisibility(isVisible)
                        Spacer(modifier = Modifier.height(10.dp))
                        AnimateByInteger(isRound)

                    }

                }
            }
        }
    }
}

@Composable
fun AnimateVisibility(
    isVisible: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        //Wrapper composable to animate the visibility changes for our composable.
        AnimatedVisibility(
            visible = isVisible,
            //The animation when composable becomes visible:
            enter = slideInHorizontally() + fadeIn(),
            //The animation when composable becomes invisible:
            exit = slideOutHorizontally() + fadeOut(),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
            )
        }
    }
}

@Composable
fun AnimateByInteger(
    isRound: Boolean
) {
    val borderRadius by animateIntAsState(
        //After half a second animation starts and takes 3 seconds to finish.
        targetValue = if (isRound) 100 else 0,
        animationSpec = tween(
            durationMillis = 3000,
            delayMillis = 500,
        )

        /*
        //Animate composable with a bouncy animation
        targetValue = if (isRound) 40 else 20,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessVeryLow
        )*/
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(borderRadius))
            .background(Color.Green)
    ) {

    }
}