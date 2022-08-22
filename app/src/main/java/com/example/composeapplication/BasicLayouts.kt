package com.example.composeapplication

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapplication.ui.theme.Chartreuse
import com.example.composeapplication.ui.theme.ComposeApplicationTheme

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(56.dp),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = Color.Gray
            )
        },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        value = "",
        onValueChange = {},

        )
}

@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier,
    @DrawableRes imageResource: Int = R.drawable.a,
    @StringRes text: Int = R.string.lorem_ipsum
) {
    Column(
        modifier = modifier.padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .padding(top = 4.dp)
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(text)
        )

        Text(
            text = stringResource(text),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = modifier
                .width(90.dp)
                .paddingFromBaseline(
                    top = 24.dp,
                    bottom = 8.dp
                ),
            style = MaterialTheme.typography.h3,
            fontSize = 16.sp
        )
    }
}


//@Preview()
@Composable
fun SearchBarPreview() {
    ComposeApplicationTheme {
        SearchBar(Modifier.padding(horizontal = 8.dp))
    }
}

//@Preview()
@Composable
fun AlignYourBodyElementPreview() {
    ComposeApplicationTheme {
        AlignYourBodyElement()
    }
}