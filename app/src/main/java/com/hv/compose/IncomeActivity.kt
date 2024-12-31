package com.hv.compose

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.hv.compose.bean.UnionIncomeBean
import com.hv.compose.ui.theme.ComposeTheme
import com.hv.compose.util.DimenUtil

class IncomeActivity: ComponentActivity() {
    //首先，将mutableListOf 改成mutableStateListOf，用于可记录数据状态
    private val mData = mutableStateListOf<UnionIncomeBean>()

    private var isNoMoreDataShow = mutableStateOf(false)


    override fun onCreate(saveInstanceState : Bundle?){
        super.onCreate(saveInstanceState)
        //这行代码只是强制页面竖屏显示，各位看官根据需要来决定是否加这行代码
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ContentUI()
        }
        //在onCreate里面获取初始数据就可以了，发现了compose的好处了吗？先生成的UI，
        //UI里面遍历了mData，那么只要mData的值改变，UI就会自动改变，这就是声明式UI
        getData()
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
            setListData()
        }
    }

    @Composable
    fun setListData(){
        //创建一个可记录状态的参数
        val listState = rememberLazyListState()
        //设置滑动监听事件
        val scrollListener = remember {
            object : NestedScrollConnection {
                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    //当第一条可见的下标 + 界面上一共可见的item数量 >总数量-2时，就去再获取下一页的数据
                    //这里的判断基本表现为是滑动到倒数第三条时开始加载下一页
                    if(listState.firstVisibleItemIndex+listState.layoutInfo.visibleItemsInfo.size>mData.size-2){
                        getData()
                    }
                    return super.onPostScroll(consumed, available, source)
                }
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .nestedScroll(scrollListener), //这里添加监听
            state = listState //记录状态
        ) {
            //遍历数据列表
            items(mData.size) {
                Column {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(113.dp)
                            .padding(start = 16.dp, end = 16.dp)){
                        val drawable: Drawable = ContextCompat.getDrawable(this@IncomeActivity, R.drawable.block)!!
                        //这里下面的Image，我是根据UI效果，自己自定义实现的View，所以用了一下这种写法，pixelBlockDrawable的代码我就不贴了，各位看官可以直接用一张灰白色图片替代
                        val bitmap = drawableToBitamp(drawable,
                                DimenUtil.getScreenWidth(this@IncomeActivity) - DimenUtil.dp2px(this@IncomeActivity, 32f),
                                DimenUtil.dp2px(this@IncomeActivity, 113f))

                        Image(bitmap = bitmap,
                            contentDescription = "" ,
                            modifier = Modifier.fillMaxWidth().height(113.dp),
                            contentScale = ContentScale.FillWidth)
                        //这里加载网络图片，我用到了coil的compose，如果发现AsyncImage没法使用的
                        //请先在build.gradle下面添加coil的compose依赖：api 'io.coil-kt:coil-compose:2.4.0'
                        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                            .data(mData[it].img)
                            .transformations(RoundedCornersTransformation(DimenUtil.dp2px(this@IncomeActivity,8f).toFloat()))
                            .scale(Scale.FILL)
                            .build(), contentDescription = "",
                            Modifier
                                .padding(top = 12.dp)
                                .width(56.dp)
                                .height(56.dp)
                        )

                        Text(text = mData[it].name, Modifier
                            .padding(top = 15.dp,start = 68.dp),
                            color = Color(0xffffffff),
                            fontSize = 16.sp
                        )

                        Text(text = "ID: ${mData[it].uid}",
                            Modifier.padding(start = 68.dp,top = 45.dp),
                            color = Color(0x99ffffff),
                            fontSize = 14.sp)

                        Row(
                            Modifier
                                .fillParentMaxWidth()
                                .padding(start = 12.dp, top = 80.dp)){

                            Text(text = "今日流水:",
                                color = Color(0x99ffffff),
                                fontSize = 12.sp
                            )

                            Text(text = mData[it].todayIncome,
                                Modifier.padding(start = 5.dp),
                                color = Color(0xffaffba1),
                                fontSize = 12.sp
                            )

                            Text(text = "今日流水:",
                                Modifier.padding(start = 16.dp),
                                color = Color(0x99ffffff),
                                fontSize = 12.sp
                            )

                            Text(text = mData[it].yesterdayIncome,
                                Modifier.padding(start = 5.dp),
                                color = Color(0xffaffba1),
                                fontSize = 12.sp
                            )
                        }

                    }
                    //每条渲染完毕之后，加个间距
                    Spacer(modifier = Modifier.height(12.dp))
                }

            }

            if(isNoMoreDataShow.value) { //当我们上面记录的状态为true，代表没有更多数据了
                item {
                    Box(Modifier.fillMaxWidth().height(40.dp)) {
                        Text(
                            text = "没有更多数据啦",
                            modifier = Modifier.align(alignment = Alignment.Center),
                            color = Color(0x99ffffff),
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }

    fun getData(){
        if(mData.size>=20){
            isNoMoreDataShow.value =true
            return
        }
        for(i in 1..10){
            mData.add(UnionIncomeBean("https://img2.baidu.com/it/u=3565369971,2082314928&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=800","测试房间$i",1183301+i,
                "9999$i","4444$i"))
        }
    }


    private fun drawableToBitamp(drawable: Drawable, width: Int, height: Int): ImageBitmap {
        return when (drawable) {
            is BitmapDrawable -> drawable.bitmap.asImageBitmap()
            is VectorDrawable -> {
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                bitmap.asImageBitmap()
            }
            else -> {
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)
                bitmap.asImageBitmap()
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun PreviewUI(){
        ContentUI()
    }
}