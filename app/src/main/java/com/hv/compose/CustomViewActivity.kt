package com.hv.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.hv.compose.ui.theme.ComposeTheme
import com.hv.compose.view.DrawingView
import com.hv.compose.view.StickerObject
import com.hv.compose.view.TextObject

class CustomViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                DrawBoardScreen()
            }
        }
    }
}

@Composable
fun DrawBoardScreen() {
    var currentTextObject by remember { mutableStateOf<TextObject?>(null) }
    var currentStickerObject by remember { mutableStateOf<StickerObject?>(null) }
    var isVisible by remember { mutableStateOf(false) }
    var textToAdd by remember { mutableStateOf<String?>(null) }

    // 定义一个保存 drawingView 引用的变量
    var drawingViewRef by remember { mutableStateOf<DrawingView?>(null) }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        // 头部
        Header(onBackClick = { /* Handle Back Click */ }, onDoneClick = { /* Handle Done Click */ })

        Row(modifier = Modifier.fillMaxSize()) {
            // 工具栏
            ToolBar(
                onBrushClick = { /* Handle Brush Click */ },
                onPencilClick = { /* Handle Pencil Click */ },
                onTextClick = {
                    // 设置要添加的文本对象，默认文本或用户输入的文本
                    textToAdd = "Hello 广汽"
                },
                onColorsClick = { /* Handle Colors Click */ },
                onBgClick = {
                    // 在此获取 Context，并设置背景
                    val drawable = ContextCompat.getDrawable(context, R.drawable.imagebg)
                    drawable?.let {
                        drawingViewRef?.setBackground(it) // 设置背景
                    }
                },
                onEraserClick = { /* Handle Eraser Click */ },
                onRestartClick = { /* Handle Restart Click */ }
            )

            // 绘图区域
            DrawingArea(
                currentTextObject = currentTextObject,
                currentStickerObject = currentStickerObject,
                onTextSelected = { textObject ->
                    currentTextObject = textObject
                    isVisible = true
                },
                onStickerSelected = { stickerObject ->
                    currentStickerObject = stickerObject
                },
                onNothingSelected = {
                    currentTextObject = null
                    currentStickerObject = null
                    isVisible = false
                },
                textToAdd = textToAdd,
                onTextAdded = { textToAdd = null },
                drawingViewRef = { drawingViewRef = it } // 保存 DrawingView 引用
            )
        }

        // 文本编辑框
        if (isVisible && currentTextObject != null) {
            TextEditArea(
                textObject = currentTextObject!!,
                onTextChanged = { newText ->
                    // 在文本框中更新文本时更新 TextObject 对象的文本
                    currentTextObject?.setText(newText)
                },
                onDeleteClick = { /* Handle Delete */ },
                onConfirmClick = { /* Handle Confirm */ }
            )
        }
    }
}

@Composable
fun Header(onBackClick: () -> Unit, onDoneClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Gray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(painter = painterResource(id = R.drawable.back), contentDescription = null)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "完成",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(end = 30.dp),
        )
    }
}

@Composable
fun ToolBar(
    onBrushClick: () -> Unit,
    onPencilClick: () -> Unit,
    onTextClick: () -> Unit,
    onColorsClick: () -> Unit,
    onBgClick: () -> Unit,
    onEraserClick: () -> Unit,
    onRestartClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(70.dp)
            .fillMaxHeight()
            .background(Color(0xFFBACCDA))
            .padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToolBarButton(iconRes = R.drawable.bursh, onClick = onBrushClick)
        ToolBarButton(iconRes = R.drawable.pencil, onClick = onPencilClick)
        ToolBarButton(iconRes = R.drawable.text, onClick = onTextClick)
        ToolBarButton(iconRes = R.drawable.colors, onClick = onColorsClick)
        ToolBarButton(iconRes = R.drawable.bg, onClick = {onBgClick()})
        ToolBarButton(iconRes = R.drawable.eraser, onClick = onEraserClick)

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = onRestartClick) {
            Icon(
                painter = painterResource(id = R.drawable.restart),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
    }
}

@Composable
fun ToolBarButton(iconRes: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(45.dp)
                .padding(bottom = 5.dp)
        )
    }
}

@Composable
fun DrawingArea(
    currentTextObject: TextObject?,
    currentStickerObject: StickerObject?,
    onTextSelected: (TextObject) -> Unit,
    onStickerSelected: (StickerObject) -> Unit,
    onNothingSelected: () -> Unit,
    textToAdd: String?,
    onTextAdded: () -> Unit,
    drawingViewRef: (DrawingView) -> Unit
) {
    AndroidView(
        factory = { context ->
            DrawingView(context).apply {
                setOnTouch(object : DrawingView.OnTouch {
                    override fun currentTextOjectUse(currentObject: TextObject) {
                        onTextSelected(currentObject)
                    }

                    override fun currentTextOjectMove(currentTextObject: TextObject) {
                        // Handle text move as needed
                    }

                    override fun currentOjectNone() {
                        onNothingSelected()
                    }

                    override fun currentStickerOjectUse(currentStickerObject: StickerObject) {
                        onStickerSelected(currentStickerObject)
                    }

                    override fun currentStickerOjectMove(currentStickerObject: StickerObject) {
                        // Handle sticker move as needed
                    }
                })
                drawingViewRef(this) // 保存 DrawingView 引用
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { view ->
            // 如果有文本需要添加，则调用 addTextObject 方法
            textToAdd?.let {
                view.addTextObject(it)
                onTextAdded()
            }
        }
    )
}

@Composable
fun TextEditArea(
    textObject: TextObject,
    onTextChanged: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    var text by remember { mutableStateOf(textObject.text) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                Color.Black.copy(alpha = 0.7f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )
    ) {
        BasicTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                onTextChanged(newText)
            },
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .background(Color.Transparent)
        )
        Button(onClick = onDeleteClick) {
            Text("删除")
        }
        Button(onClick = onConfirmClick) {
            Text("确定")
        }
    }
}
