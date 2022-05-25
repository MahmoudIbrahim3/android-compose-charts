package com.mahmoud.composechartssamples

import androidx.compose.ui.graphics.Color
import com.mahmoud.composecharts.barchart.BarChartEntity

val barChartData = listOf(
    BarChartEntity(150.0f, Color(0xFF618A32), "1"),
    BarChartEntity(450.0f, Color(0xFFC32A33), "2"),
    BarChartEntity(300.0f, Color.Blue, "3"),
    BarChartEntity(150.0f, Color.Cyan, "4"),
    BarChartEntity(500.0f, Color.Magenta, "5")
)

val barChartData2 = listOf(
    BarChartEntity(150.0f, Color(0xFF618A32), "First"),
    BarChartEntity(450.0f, Color(0xFFC32A33), "Second")
)

val barChartData3 = listOf(
    BarChartEntity(300.0f, Color.Gray, "2020"),
    BarChartEntity(400.0f, Color.Black, "2021"),
    BarChartEntity(150.0f, Color.DarkGray, "2022")
)

val barChartData4 = listOf(
    BarChartEntity(3.0f, Color(0xFFC32A33), ""),
    BarChartEntity(5.0f, Color.Blue, ""),
    BarChartEntity(2.0f, Color.DarkGray, ""),
    BarChartEntity(8.0f, Color.Cyan, ""),
    BarChartEntity(2.0f, Color.Magenta, ""),
    BarChartEntity(9.0f, Color(0xFFC32A33), ""),
    BarChartEntity(0.5f, Color.LightGray, ""),
    BarChartEntity(10.0f, Color.Green, ""),
    BarChartEntity(4.0f, Color.Gray, ""),
    BarChartEntity(7.0f, Color(0xFF618A32), ""),
)

val verticalAxisValues = listOf(0.0f, 100.0f, 200.0f, 300.0f, 400.0f, 500.0f)

val verticalAxisValues2 = listOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f)
