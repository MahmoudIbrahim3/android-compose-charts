package com.mahmoud.composecharts

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mahmoud.composecharts.ui.theme.DefaultAxisLabelColor
import com.mahmoud.composecharts.ui.theme.DefaultAxisLabelFontSize
import com.mahmoud.composecharts.ui.theme.DefaultBarWidth
import com.mahmoud.composecharts.ui.theme.DefaultGridLinesColor

data class ChartElements(
    val showVerticalLine: Boolean = false,
    val showGridLines: Boolean = true,
    val showHorizontalLabels: Boolean = true,
    val showLegend: Boolean = true,
    val gridLinesColor: Color = DefaultGridLinesColor,
    val labelColor: Color = DefaultAxisLabelColor,
    val barWidth: Dp = DefaultBarWidth,
    val legendWidth: Dp = DefaultBarWidth,
    val fontSize: TextUnit = DefaultAxisLabelFontSize,
)
