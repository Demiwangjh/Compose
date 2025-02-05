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
import androidx.compose.ui.util.fastForEachIndexed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomThemePack(
    onNext: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by rememberSaveable { mutableIntStateOf(0) }
    val categories = listOf("内置", "创作")

    Column(
        modifier = modifier
    ) {
        LampList(
            modifier = Modifier
        )
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            if (selectedCategory == 0) {
                Previewer(
                    imageResource = R.drawable.preview_sidp
                )
            } else if (selectedCategory == 1) {
                Previewer(
                    imageResource = R.drawable.preview_sidp_creation
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
                        bitmap = ImageBitmap.imageResource(R.drawable.motionpicture_creation_add),
                        contentDescription = "创建",
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .clickable {
                                onNext()
                            }
                    )
                        Text(
                            text = "创建",
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
                    bitmap = ImageBitmap.imageResource(R.drawable.off_sidp),
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
                MotionPictureList(
                    category = selectedCategory,
                    modifier = Modifier
                )
            }
        }
    }
}

val sampleData4Lamps = listOf(
    "后视镜投影灯",
    "前智能交互灯",
    "后智能交互灯",
    "灯具 #4",
    "灯具 #5",
    "灯具 #6",
    "灯具 #7",
    "灯具 #8",
    "灯具 #9",
    "灯具 #A",
    "灯具 #B",
    "灯具 #C",
    "灯具 #D",
    "灯具 #E",
    "灯具 #F",
    "灯具 #0",
)

@Composable
fun LampList(modifier: Modifier = Modifier) {
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    ScrollableTabRow (
        selectedTabIndex = selectedIndex,
        modifier = modifier
    ) {
        sampleData4Lamps.fastForEachIndexed {i, v ->
            Tab(
                selected = (i == selectedIndex),
                text = {
                    Text(
                        v
                    )
                },
                onClick = {
                    selectedIndex = i
                }
            )
        }
    }
}