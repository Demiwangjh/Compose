package com.hv.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

data class ThemePackListItemData (
    val name: String,
    @DrawableRes val imageSource: Int
)

val sampleData4ThemePackPreset = listOf(
    ThemePackListItemData(
        name = "璀璨星河",
        imageSource = R.drawable.theme_applied
    ),
    ThemePackListItemData(
        name = "人间烟火",
        imageSource = R.drawable.theme
    ),
    ThemePackListItemData(
        name = "大好河山",
        imageSource = R.drawable.theme_selected
    ),
    ThemePackListItemData(
        name = "主题包 #4",
        imageSource = R.drawable.theme
    ),
    ThemePackListItemData(
        name = "主题包 #5",
        imageSource = R.drawable.theme
    ),
    ThemePackListItemData(
        name = "主题包 #6",
        imageSource = R.drawable.theme
    ),
    ThemePackListItemData(
        name = "主题包 #7",
        imageSource = R.drawable.theme
    ),
    ThemePackListItemData(
        name = "主题包 #8",
        imageSource = R.drawable.theme
    ),
    ThemePackListItemData(
        name = "主题包 #9",
        imageSource = R.drawable.theme
    ),
)

val sampleData4ThemePackCustom = listOf(
    ThemePackListItemData(
        name = "定制 #1",
        imageSource = R.drawable.theme_custom_selected
    ),
    ThemePackListItemData(
        name = "定制 #2",
        imageSource = R.drawable.theme_custom
    ),
    ThemePackListItemData(
        name = "定制 #3",
        imageSource = R.drawable.theme_custom
    ),
    ThemePackListItemData(
        name = "定制 #4",
        imageSource = R.drawable.theme_custom
    ),
)

@Composable
fun ThemePackList(
    category: Int,
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier.height(100.dp)
    ) {
        if (category == 0) {
            items(sampleData4ThemePackPreset) { data ->
                ThemePackListItem(
                    data = data
                )
            }
        } else if (category == 1) {
            items(sampleData4ThemePackCustom) { data ->
                ThemePackListItem(
                    data = data
                )
            }
        }
    }
}

@Composable
fun ThemePackListItem(
    data: ThemePackListItemData,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(data.imageSource),
            contentDescription = "Theme",
            modifier = modifier.clip(RoundedCornerShape(5.dp))
        )
        Text(
            text = data.name,
            style = MaterialTheme.typography.bodySmall
        )
    }
}