package com.example.composeapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())

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
                        Row {
                            AnimateContentSize(isVisible)
                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        AnimateVisibility(isVisible)
                        Row {
                            AnimateMultipleValues(isRound)
                            Spacer(modifier = Modifier.width(10.dp))
                            AnimateByInteger(isRound)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            InfiniteAnimation()
                            Spacer(modifier = Modifier.width(10.dp))
                            AnimateContents(isVisible)
                        }


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

@Composable
fun AnimateMultipleValues(
    isRound: Boolean
) {
    val transition = updateTransition(targetState = isRound, label = null)
    val borderRadius by transition.animateInt(
        transitionSpec = { tween(1000) },
        label = "",
        targetValueByState = { isRound ->
            if (isRound) 100 else 0
        }
    )
    val color by transition.animateColor(
        transitionSpec = { tween(1000) },
        label = "",
        targetValueByState = { isRound ->
            if (isRound) Color.Red else Color.Green
        }
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .clip(RoundedCornerShape(borderRadius))
            .background(color)
    )
}


@Composable
fun InfiniteAnimation() {
    val transition = rememberInfiniteTransition()
    val color by transition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color)
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateContents(
    isVisible: Boolean
) {
    AnimatedContent(
        targetState = isVisible,
        modifier = Modifier.size(200.dp),
        content = { visible ->
            if (visible)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Second screen")
                }
            else
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "First screen")
                }
        },
        transitionSpec = {
            // A simple fade animation to change content
            //fadeIn(animationSpec = tween(1000)) with fadeOut(animationSpec = tween(1000))

            slideInHorizontally { if (isVisible) -it else it } with slideOutHorizontally { if (isVisible) it else -it }
        },
    )
}

@Composable
fun AnimateContentSize(
    isVisible: Boolean
) {
    val descriptionText = stringResource(id = R.string.lorem_ipsum)
    val context = LocalContext.current.applicationContext
    Text(
        text = descriptionText,
        maxLines = if (isVisible) 10 else 1,
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                ),
                finishedListener = { _, i ->
                    Toast.makeText(context, "Some toast", Toast.LENGTH_SHORT).show()
                }
            )
    )
}