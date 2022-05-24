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

val verticalAxisValues = listOf(0.0f, 100.0f, 200.0f, 300.0f, 400.0f, 500.0f)
