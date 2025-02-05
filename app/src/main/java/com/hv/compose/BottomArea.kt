package com.hv.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource

@Composable
fun BottomArea(modifier: Modifier = Modifier) {
    Image(
        bitmap = ImageBitmap.imageResource(R.drawable.toolbar),
        contentDescription = "Toolbar",
        modifier = modifier
    )
}