package com.example.composeapplication

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composeapplication.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun Navigation() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}

@Destination(start = true)
@Composable
fun MainScreen(
    navigator: DestinationsNavigator
) {
    var text by remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                if (text.isNotBlank())
                    navigator.navigate(
                        DetailScreenDestination(text)
                    )
                else
                    Log.i("!!!", "MainScreen: Text is Empty")
            }
        ) {
            Text(text = "Navigate to Detail Screen")
        }
    }
}

@Destination
@Composable
fun DetailScreen(name: String?) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "hello, $name!")
    }
}