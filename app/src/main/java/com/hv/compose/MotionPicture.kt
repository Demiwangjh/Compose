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

data class MotionPictureListItemData (
    val name: String,
    @DrawableRes val imageSource: Int
)

val sampleData4MotionPictureBuiltin = listOf(
    MotionPictureListItemData(
        name = "动画 #1",
        imageSource = R.drawable.motionpicture_sidp_applied
    ),
    MotionPictureListItemData(
        name = "图案 #2",
        imageSource = R.drawable.motionpicture_sidp
    ),
    MotionPictureListItemData(
        name = "动画 #3",
        imageSource = R.drawable.motionpicture_sidp_selected
    ),
    MotionPictureListItemData(
        name = "图案 #4",
        imageSource = R.drawable.motionpicture_sidp
    ),
    MotionPictureListItemData(
        name = "动画 #5",
        imageSource = R.drawable.motionpicture_sidp
    ),
    MotionPictureListItemData(
        name = "图案 #6",
        imageSource = R.drawable.motionpicture_sidp
    ),
    MotionPictureListItemData(
        name = "动画 #7",
        imageSource = R.drawable.motionpicture_sidp
    ),
    MotionPictureListItemData(
        name = "图案 #8",
        imageSource = R.drawable.motionpicture_sidp
    ),
    MotionPictureListItemData(
        name = "动画 #9",
        imageSource = R.drawable.motionpicture_sidp
    ),
)

val sampleData4MotionPictureCretion = listOf(
    MotionPictureListItemData(
        name = "创作 #1",
        imageSource = R.drawable.motionpicture_creation_selected
    ),
    MotionPictureListItemData(
        name = "创作 #2",
        imageSource = R.drawable.motionpicture_creation
    ),
    MotionPictureListItemData(
        name = "创作 #3",
        imageSource = R.drawable.motionpicture_creation
    ),
    MotionPictureListItemData(
        name = "创作 #4",
        imageSource = R.drawable.motionpicture_creation
    ),
)

@Composable
fun MotionPictureList(
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
            items(sampleData4MotionPictureBuiltin) { data ->
                MotionPictureListItem(
                    data = data
                )
            }
        } else if (category == 1) {
            items(sampleData4MotionPictureCretion) { data ->
                MotionPictureListItem(
                    data = data
                )
            }
        }
    }
}

@Composable
fun MotionPictureListItem(
    data: MotionPictureListItemData,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = ImageBitmap.imageResource(data.imageSource),
            contentDescription = "MotionPicture",
            modifier = modifier.clip(RoundedCornerShape(5.dp))
        )
        Text(
            text = data.name,
            style = MaterialTheme.typography.bodySmall
        )
    }
}