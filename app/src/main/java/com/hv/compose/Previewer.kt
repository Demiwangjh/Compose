package com.hv.compose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource

@Composable
fun Previewer(@DrawableRes imageResource: Int, modifier: Modifier = Modifier) {
    Image(
        bitmap = ImageBitmap.imageResource(imageResource),
        contentDescription = "Previewer",
        contentScale = ContentScale.Fit,
        modifier = modifier
    )
}