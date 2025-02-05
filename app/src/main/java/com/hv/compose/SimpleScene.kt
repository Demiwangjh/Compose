package com.hv.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleScene(modifier: Modifier = Modifier) {
    val datas = listOf("开门预警", "转向投影", "自动泊车", "视频投影", "简易 #5")

    Column {
        ScrollableTabRow (
            selectedTabIndex = 0,
            modifier = Modifier.width(160.dp)
        ) {
            Tab(
                selected = false,
                text = {
                    Text(
                        "光影魔法App",
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                onClick = {}
            )
        }
        Column(
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            datas.forEach { v ->
                SimpleSceneItem(v)
            }
        }
    }
}

@Composable
fun SimpleSceneItem(name: String, checked: Boolean = true, modifier: Modifier = Modifier) {
    var checked by rememberSaveable { mutableStateOf(checked) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        AssistChip(
            label = {
                Text(
                    text = "$name",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            onClick = {},
            leadingIcon = {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = ""
                )
            },
            modifier = Modifier.width(110.dp)
        )
        Spacer(
            modifier = modifier.size(5.dp)
        )
        Switch(
            checked = checked,
            onCheckedChange = {newValue -> checked = newValue}
        )
    }
}