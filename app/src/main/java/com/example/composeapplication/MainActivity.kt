package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {

                WellnessScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}
/*
@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 320,
)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {
        MyApp()
    }
}


@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Composable
fun DefaultPreviewDark() {
    ComposeApplicationTheme {
        MyApp()
    }
}*/

@Composable
fun MyApp() {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    return if (shouldShowOnboarding) {
        OnBoardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
fun OnBoardingScreen(
    onContinueButtonClicked: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome to the Basics Codelab!",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = onContinueButtonClicked,
                modifier = Modifier.clip(RoundedCornerShape(5.dp))
            ) {
                Text(
                    text = "Continue",
                    modifier = Modifier.padding(all = 4.dp)
                )
            }
        }
    }
}


@Composable
fun Greetings(names: List<String> = List(100) { "list num.$it" }) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {

    var expanded by remember {
        mutableStateOf(false)
    }

    /*
    Documents:
    https://developer.android.com/jetpack/compose/animation

    animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow
        )


     animationSpec = tween(
            durationMillis = 500,
            delayMillis = 50
        )

    animationSpec = repeatable(
        iterations = 5,
        animation = tween(
            durationMillis = 300,
        ),
        repeatMode = RepeatMode.Reverse
    )

    animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 300),
            repeatMode = RepeatMode.Reverse
        )

    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500,
            delayMillis = 50
        )
    )
    */
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(
            vertical = 4.dp,
            horizontal = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(0.9f)
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            delayMillis = 20
                        )
                    )
            ) {
                Text(text = "Hello, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                if (expanded)
                    Text(text = stringResource(id = R.string.lorem_ipsum).repeat(2))
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector =
                    if (expanded)
                        Icons.Filled.ExpandLess
                    else
                        Icons.Filled.ExpandMore,
                    contentDescription =
                    if (expanded)
                        stringResource(id = R.string.show_less)
                    else
                        stringResource(id = R.string.show_more)
                )
            }
        }

    }
}
