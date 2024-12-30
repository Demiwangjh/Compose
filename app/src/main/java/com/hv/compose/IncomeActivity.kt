package com.hv.compose

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.hv.compose.ui.theme.ComposeTheme
import com.hv.compose.util.DimenUtil

class IncomeActivity: ComponentActivity() {
    override fun onCreate(saveInstanceState : Bundle?){
        super.onCreate(saveInstanceState)
        //这行代码只是强制页面竖屏显示，各位看官根据需要来决定是否加这行代码
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ContentUI()
        }
    }

    @Composable
    fun ContentUI(){
        ComposeTheme {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black
            ) {
                Box(Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.tree),
                        contentDescription = null,
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                    Column {
                        Spacer(
                            //这里的DimenUtil和StatusBarUtils都是工具类，px2dp就是将px转dp的
                            //getStatusBarHeight就是获取状态栏的高度，大家可以自行百度找代码
                            modifier = Modifier.height(
                                DimenUtil.px2dp(this@IncomeActivity, 20f).dp)
                        )

                        Title()
                        Content()
                    }
                }
            }
        }
    }

    @Composable
    fun Title(){
        Box(modifier = Modifier.fillMaxWidth()){
            Image(
                painter = painterResource(id = R.drawable.tree),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(12.dp)
                    .clickable {
                        finish()
                    }
            )

            Text(
                text = "收益明细",
                color = Color(0xffffffff),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(alignment = Alignment.Center)
            )
        }
    }

    @Composable
    fun Content(){
        Column(
            Modifier
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .fillMaxWidth()
                .background(Color.Transparent)){

        }
    }

    @Preview(showBackground = true)
    @Composable
    fun previewUI(){
        ContentUI()
    }
}