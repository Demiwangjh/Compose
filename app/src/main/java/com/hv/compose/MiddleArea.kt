package com.hv.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MiddleArea(
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        SimpleScene(
            modifier = Modifier
        )
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        VerticalDivider(
            modifier = Modifier
        )
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        ComplexScene(
            onNext = onNext,
            modifier = Modifier.fillMaxWidth()
        )
    }
}