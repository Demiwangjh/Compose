package com.hv.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.hv.compose.ui.theme.ComposeTheme

class ConstraintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintLayout{
                        val (ivMask, tv_text) = createRefs() //这里就是创建一个ID的意思，用于标记
                        Image(painter = painterResource(id = R.drawable.block), contentDescription = null,
                            Modifier
                                .constrainAs(ivMask) { //这里应用上这个ID
                                    //下面这行对应xml中的app:layout_constraintTop_toTopOf="parent"
                                    //但很友好的,官方还将margin的参数也在这里一并传入了，可以点进去看一下
                                    //其实它有3个参数，第二个是margin，第三个是当布局隐藏时的margin
                                    top.linkTo(parent.top, 26.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .width(356.5.dp)
                                .height(49.dp))

                        //有了上面这个ID之后，接下来如果有布局需要根据上面的图片进行位置的调整，就好办了
                        Text(
                            text ="Hello Android",
                            //当如果你这个控件不会被别的控件所依赖，就可以直接在这里写createRef()
                            // Modifier.constrainAs(createRef()) {
                            Modifier.constrainAs(tv_text) {
                                //等同于app:layout_constraintTop_toTopOf="@id/iv_mask"
                                top.linkTo(ivMask.top)
                                bottom.linkTo(ivMask.bottom)
                                start.linkTo(ivMask.start)
                                end.linkTo(ivMask.end)
                            }, color = Color.White, fontSize = 14.sp, textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}