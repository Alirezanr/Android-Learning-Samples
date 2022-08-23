package com.example.composeapplication

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
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

data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

private val alignYourBodyData = listOf(
    R.drawable.a to R.string.ab1_inversions,
    R.drawable.b to R.string.ab2_quick_yoga,
    R.drawable.c to R.string.ab3_stretching,
    R.drawable.d to R.string.ab4_tabata,
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.w to R.string.fc1_short_mantras,
    R.drawable.x to R.string.fc2_nature_meditations,
    R.drawable.y to R.string.fc3_stress_and_anxiety,
    R.drawable.z to R.string.fc4_self_massage,
).map { DrawableStringPair(it.first, it.second) }


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
    @DrawableRes imageResource: Int,
    text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = modifier
                .size(88.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = imageResource),
            contentDescription = text
        )

        Text(
            text = text,
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
fun AlignYourBodyRow(
    modifier: Modifier = Modifier,
    alignYourBodyData: List<DrawableStringPair>
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth(),
        //space between items
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        //space in start and end of row
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(alignYourBodyData) { item ->
            AlignYourBodyElement(
                modifier = modifier,
                imageResource = item.drawable,
                text = stringResource(id = item.text)
            )
        }
    }
}

@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes image: Int
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

@Composable
fun FavoriteCollectionGrid(
    modifier: Modifier = Modifier,
    favoriteCollectionList: List<DrawableStringPair>
) {
    LazyHorizontalGrid(
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.height(120.dp),
        rows = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(favoriteCollectionList) { item ->
            FavoriteCollectionCard(
                title = stringResource(id = item.text),
                image = item.drawable
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
        AlignYourBodyElement(
            imageResource = R.drawable.a,
            text = stringResource(id = R.string.lorem_ipsum)
        )
    }
}

//@Preview()
@Composable
fun AlignYourBodyRowPreview() {
    ComposeApplicationTheme {
        AlignYourBodyRow(
            alignYourBodyData = alignYourBodyData
        )
    }
}


//@Preview()
@Composable
fun FavoriteCollectionCardPreview() {
    ComposeApplicationTheme {
        FavoriteCollectionCard(
            title = stringResource(id = R.string.nature_meditations),
            image = R.drawable.w
        )
    }
}

//@Preview()
@Composable
fun FavoriteCollectionGridPreview() {
    ComposeApplicationTheme {
        FavoriteCollectionGrid(
            favoriteCollectionList = favoriteCollectionsData
        )
    }
}