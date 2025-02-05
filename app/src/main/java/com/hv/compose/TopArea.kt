package com.hv.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource

@Composable
fun TopArea(modifier: Modifier = Modifier) {
    Image(
        bitmap = ImageBitmap.imageResource(R.drawable.statusbar),
        contentDescription = "",
        modifier = modifier
    )
}