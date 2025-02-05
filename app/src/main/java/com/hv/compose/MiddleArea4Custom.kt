package com.hv.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MiddleArea4Custom(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
    ) {
        Column {
            ScrollableTabRow (
                selectedTabIndex = 0,
                modifier = Modifier.width(150.dp)
            ) {
                Tab(
                    selected = false,
                    text = {
                        AssistChip(
                            label = {
                                Text(
                                    text = "返回",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            onClick = {
                                onBack()
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = ""
                                )
                            }
                        )
                    },
                    onClick = {}
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ElevatedButton(
                    onClick = onBack,
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(
                        text = "保存",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Button(
                    onClick = onBack,
                    modifier = Modifier.width(150.dp)
                ) {
                    Text(
                        text = "取消",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        VerticalDivider(
            modifier = Modifier
        )
        Spacer(
            modifier = Modifier.size(8.dp)
        )
        CustomThemePack(
            onNext = onNext,
            onBack = onBack,
            modifier = Modifier.fillMaxWidth()
        )
    }
}