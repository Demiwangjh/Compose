package com.hv.compose.bean

data class UnionIncomeBean(
    val img: String,       // 图片地址
    val name: String,       // 房间名称
    val uid: Int,           // 房间编号，使用Long类型以适应更大的数字
    val todayIncome: String,         // 例如："9999$i"，可以是任何含义的值
    val yesterdayIncome: String          // 例如："4444$i"，可以是任何含义的值
)