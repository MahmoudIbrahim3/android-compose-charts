package com.mahmoud.composecharts.barchart

import androidx.compose.ui.graphics.Color

data class BarChartEntity(
    val value: Float,
    val color: Color,
    val label: String? = ""
)
