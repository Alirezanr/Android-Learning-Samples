package com.example.composeapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                Surface {
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }

}

@Composable
fun MessageCard(msg: Message) {
    // We keep track if the message is expanded or not in this
    // variable
    var isExpanded by remember { mutableStateOf(false) }
    // surfaceColor will be updated gradually from one color to the other
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 8.dp)
        .clickable {
            isExpanded = !isExpanded
        }) {

        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(
                    1.5.dp,
                    MaterialTheme.colors.secondary,
                    CircleShape
                ),
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.5.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier
                        .padding(all = 4.dp)
                        .fillMaxWidth(),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
/*
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun Preview() {
    PreviewConversation()
}*/

//Previews:
@Composable
fun PreviewMessageCard() {
    ComposeApplicationTheme {
        MessageCard(
            msg = Message(
                author = "Colleague",
                body = "Hey, take a look at Jetpack Compose, it's great!"
            )
        )
    }
}

@Composable
fun PreviewConversation() {
    ComposeApplicationTheme {
        Surface {
            Conversation(SampleData.conversationSample)
        }
    }
}

data class Message(val author: String, val body: String)
