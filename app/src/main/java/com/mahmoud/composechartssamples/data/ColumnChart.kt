package com.mahmoud.composechartssamples.data

import androidx.compose.ui.graphics.Color
import com.mahmoud.composecharts.columnchart.SeriesData

val columnChartSeriesData = listOf(
    SeriesData(listOf(4f, 2.5f, 3.5f, 4.5f), Color(0xff4472c4), "Series1"),
    SeriesData(listOf(2.5f, 4.5f, 1.5f, 3f), Color(0xffed7d31), "Series3"),
    SeriesData(listOf(2.0f, 2f, 3f, 5f), Color.Gray, "Series4"),
)

val columnChartCategoriesData = listOf("Cat1", "Cat2", "Cat3", "Cat4")


val columnChartSeriesData2 = listOf(
    SeriesData(listOf(4f, 2.5f, 3.5f), Color.Blue, "Series1"),
    SeriesData(listOf(2.5f, 4.5f, 1.5f), Color.Green, "Series3")
)

val columnChartCategoriesData2 = listOf("Cat1", "Cat2")
