package com.example.composeapplication

import android.os.Bundle
import android.view.ContextMenu
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
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
                        //MotionLayout sample:-----------------------------
                        var progress by remember {
                            mutableStateOf(0f)
                        }
                        ProfileHeader(progress = progress)
                        Spacer(modifier = Modifier.height(32.dp))
                        Slider(
                            value = progress,
                            onValueChange = {
                                progress = it
                            },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        //Animations:--------------------------------------
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

                        AnimateFloatAsState(isVisible)
                        Spacer(modifier = Modifier.height(10.dp))
                        AnimateContentSize(isVisible)


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

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileHeader(
    progress: Float
) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxSize()
    ) {
        //get properties of constraint with id of 'profile_pic'
        val properties = motionProperties(id = "profile_pic")

        Box(
            modifier = Modifier
                .layoutId("box")
                .fillMaxWidth()
                .background(Color.DarkGray)
        )
        Image(
            painter = painterResource(id = R.drawable.profile_pic),
            contentDescription = null,
            modifier = Modifier
                .layoutId("profile_pic")
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = properties.value.color("background"),
                    shape = CircleShape
                )
        )
        Text(
            text = "AlirezaNR",
            fontSize = 24.sp,
            modifier = Modifier.layoutId("username"),
            color = properties.value.color("background"),
        )
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

    val rotationAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 10000,
                easing = LinearEasing
            )
        )
    )

    Box(
        modifier = Modifier
            .size(200.dp)
            .rotate(rotationAnimation.value)
            .padding(32.dp)
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

@Composable
fun AnimateFloatAsState(
    isVisible: Boolean
) {
    val progress by animateFloatAsState(
        targetValue = if (isVisible) 0.75f else 0.25f,
        animationSpec = tween(500)
    )

    LinearProgressIndicator(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}