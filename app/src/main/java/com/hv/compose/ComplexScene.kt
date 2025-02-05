package com.hv.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplexScene(
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by rememberSaveable { mutableIntStateOf(0) }
    val categories = listOf("预设", "定制")

    Column(
        modifier = modifier
    ) {
        ComplexSceneList(
            modifier = Modifier
        )
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            if (selectedCategory == 0) {
                Previewer(
                    imageResource = R.drawable.preview
                )
            } else if (selectedCategory == 1) {
                Previewer(
                    imageResource = R.drawable.preview_custom
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SingleChoiceSegmentedButtonRow {
                    categories.forEachIndexed { i, v ->
                        SegmentedButton(
                            label = {
                                Text(
                                    v,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            selected = (i == selectedCategory),
                            onClick = {
                                selectedCategory = i
                            },
                            shape = SegmentedButtonDefaults.itemShape(
                                index = i,
                                count = categories.size
                            )
                        )
                    }
                }
                Row {
                    ElevatedButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "应用",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    if (selectedCategory == 1) {
                        Button(
                            onClick = {}
                        ) {
                            Text(
                                text = "删除",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier
            )
            Spacer(
                modifier = Modifier.size(5.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (selectedCategory == 1) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(100.dp)
                    ) {
                        Image(
                            bitmap = ImageBitmap.imageResource(R.drawable.theme_custom_add),
                            contentDescription = "新增",
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    onNext()
                                }
                        )
                        Text(
                            text = "新增",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Spacer(
                    modifier = Modifier.size(5.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.height(100.dp)
                ) {
                    Image(
                        bitmap = ImageBitmap.imageResource(R.drawable.off),
                        contentDescription = "关闭",
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                    )
                    Text(
                        text = "关闭",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(
                    modifier = Modifier.size(5.dp)
                )
                ThemePackList(
                    category = selectedCategory,
                    modifier = Modifier
                )
            }
        }
    }
}

val sampleData4ComplexScene = listOf(
    "解闭锁投影",
    "特色位置灯",
    "开门迎宾",
    "情景模式",
    "灯光秀",
    "复杂 #6",
    "复杂 #7",
    "复杂 #8",
    "复杂 #9",
    "复杂 #A",
    "复杂 #B",
    "复杂 #C",
    "复杂 #D",
    "复杂 #E",
    "复杂 #F",
    "复杂 #0",
)

@Composable
fun ComplexSceneList(modifier: Modifier = Modifier) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    ScrollableTabRow (
        selectedTabIndex = selectedIndex,
        modifier = modifier
    ) {
        sampleData4ComplexScene.forEachIndexed {i, v ->
            Tab(
                selected = (i == selectedIndex),
                text = {
                    Text(
                        v,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                onClick = {
                    selectedIndex = i
                }
            )
        }
    }
}