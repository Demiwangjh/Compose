package com.hv.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintLayout{
                        val (ivMask,tv_text,tv_text2,tv_text3) = createRefs()
                        Image(painter = painterResource(id = R.drawable.tree), contentDescription = null,
                            Modifier
                                .constrainAs(ivMask) {
                                    top.linkTo(parent.top, 26.dp)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .width(356.5.dp)
                                .height(49.dp))

                        Text(text = "Hello Android",
                            Modifier.constrainAs(tv_text) {
                                top.linkTo(ivMask.top)
                                bottom.linkTo(ivMask.bottom)
                                start.linkTo(ivMask.start)
                                end.linkTo(ivMask.end)
                            }, color = Color.Blue, fontSize = 14.sp, textAlign = TextAlign.Center
                        )

                        ConstraintLayout(Modifier.constrainAs(createRef()){top.linkTo(tv_text.bottom)}.fillMaxWidth()){
                            Text(text = "测试内容1",
                                Modifier.constrainAs(tv_text2) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }, color = Color.Black, fontSize = 14.sp, textAlign = TextAlign.Center
                            )

                            Text(text = "哈哈哈哈",
                                Modifier.constrainAs(tv_text3) {
                                    top.linkTo(tv_text2.bottom)
                                    start.linkTo(tv_text2.start)
                                    end.linkTo(tv_text2.end)
                                }, color = Color.Red, fontSize = 14.sp, textAlign = TextAlign.Center
                            )
                        }
                    }

                }
            }
        }
    }
}