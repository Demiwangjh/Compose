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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import com.hv.compose.ui.theme.ComposeTheme
import com.hv.compose.util.DimenUtil


class HeartbeatRuleActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //这行代码只是强制页面竖屏显示，各位看官根据需要来决定是否加这行代码
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            contentUI() //为了方便预览，我将布局文件抽离了出来
        }
    }
}

@Composable
fun contentUI(){
    ComposeTheme{
        //整体的布局使用了Surface，然后Modifier.fillMaxSize表示宽高都为屏幕的宽高
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            //这里同样设置Modifier.fillMaxSize()，但这个宽高是受外部的Surface的宽高限制的
            //只有当外部的Surface的宽高为屏幕的宽高，那么fillMaxSize才能达到屏幕宽高
            //就相当于我们xml里面的match_parent
            //然后设置全局背景为黑色
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black)) {
                /**
                 *这里添加一种写法，如果背景是一张图片的话，则用到以下代码
                 *各位看官可以自己试试，上面的background是不可以设置图片的
                Image(
                painter = painterResource(id = R.mipmap.ic_common_bg),
                contentDescription = null,
                Modifier.fillMaxWidth().fillMaxHeight()
                )
                 */
                Column{
                    title()
                    content()
                }

            }
        }
    }
}

@Composable
fun title(){
    //Modifier.fillMaxWidth() 表示这个Box布局的宽度为屏幕的宽度
    //没有设置高度的情况下，高度就取决于内容的高度，自适应
    Box(Modifier.fillMaxWidth()){
        Image(painter = painterResource(id = R.drawable.tree),
            null,
            modifier = Modifier
                .size(48.dp)
                //根据UI图的话，图片的大小是24dp*24dp，为了扩大点击热点
                //所以上面设置了size为48dp，然后padding为12dp
                //这里请留意，compose里面是没有margin的，只有padding，那么到底是
                //margin还是padding就取决于上下文以及调用padding的顺序
                .padding(12.dp)
        )

        Text(
            text = "心动信号规则描述",
            //这里请留意，如果是要像我这样使用十六进制的颜色的话，一定要是argb
            //比如如果写成0xffffff是会报错的
            color = Color(0xffffffff),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(alignment = Alignment.Center)
        )
    }
}

@Composable
fun content(){
    Column(
        Modifier
            //添加代码表示布局超过一行可以滚动,就不用写ScrollView了
            .verticalScroll(rememberScrollState())
            .padding(16.dp, 16.dp, 30.dp, 0.dp)) {

        Text(text = "一、心动匹配方式描述",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 16.sp
        )

        Text(text = "内容，这里我简化了",
            Modifier.padding(top = 8.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )

        Text(text = "二、道具卡功能说明",
            Modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 16.sp
        )

        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 8.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )

        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )

        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )

        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )

        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )
        Text(
            text = "内容，这里我简化了",
            Modifier.padding(top = 12.dp,end = 16.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0x99ffffff)
        )

        Spacer(modifier = Modifier.height(82.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUI(){
    //看到抽离出来的好处了吗？调用的同一个函数，只要有修改，就能预览了
    contentUI()
}
