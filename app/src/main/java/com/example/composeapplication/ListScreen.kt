package com.example.composeapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapplication.ui.spacing
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import com.example.composeapplication.ui.utility.WindowInfo
import com.example.composeapplication.ui.utility.rememberWindowInfo


@Composable
fun ListScreen() {
    val windowInfo = rememberWindowInfo()
    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(10) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Cyan)
                        .padding(MaterialTheme.spacing.medium)
                )
            }
            items(10) {
                Text(
                    text = "Item $it",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Green)
                        .padding(MaterialTheme.spacing.medium)
                )
            }
        }
    } else {
        Row(modifier = Modifier.fillMaxWidth()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(10) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Cyan)
                            .padding(MaterialTheme.spacing.medium)
                    )
                }
            }
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(10) {
                    Text(
                        text = "Item $it",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Green)
                            .padding(MaterialTheme.spacing.medium)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ListScreenPreview() {
    ComposeApplicationTheme {
        ListScreen()
    }
}