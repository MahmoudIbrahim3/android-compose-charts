package com.mahmoud.composecharts.columnchart

import androidx.compose.ui.graphics.Color

data class SeriesData(
    val series: List<Float>,
    val color: Color,
    val seriesName: String
)
