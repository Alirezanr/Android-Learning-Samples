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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = modifier,
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


@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.nature_meditations),
    @DrawableRes image: Int = R.drawable.w
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .width(192.dp)
            .height(56.dp)
            .padding(horizontal = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = modifier.size(56.dp),
                painter = painterResource(id = image),
                contentDescription = title,
                contentScale = ContentScale.Crop
            )

            Text(
                modifier = modifier
                    .padding(start = 8.dp),
                text = title,
                maxLines = 2,
                style = MaterialTheme.typography.h3,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
            )
        }
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
        AlignYourBodyElement(modifier = Modifier.padding(horizontal = 4.dp))
    }
}

@Preview()
@Composable
fun FavoriteCollectionCardPreview() {
    ComposeApplicationTheme {
        FavoriteCollectionCard()
    }
}