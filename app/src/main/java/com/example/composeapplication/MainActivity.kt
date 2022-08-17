package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                val bottomSheetState = rememberBottomSheetState(
                    //is bottom sheet open or closed when starting
                    initialValue = BottomSheetValue.Collapsed,
                    //animation of opening and closing bottom sheet
                    /*animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy
                    )*/
                )
                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = bottomSheetState
                )
                val scope = rememberCoroutineScope()
                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetBackgroundColor = Color.Gray,
                    sheetShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    sheetPeekHeight = 0.dp,
                    sheetElevation= 5.dp,
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Bottom Sheet",
                                fontSize = 50.sp
                            )
                        }
                    }

                ) {
                    //Screen content
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            scope.launch {
                                if (bottomSheetState.isCollapsed) {
                                    bottomSheetState.expand()
                                } else {
                                    bottomSheetState.collapse()
                                }
                            }
                        }) {
                            //use fraction to implement custom animations.
                            Text(text = "Toggle ${bottomSheetState.progress.fraction}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeApplicationTheme {
        Greeting("Android")
    }
}