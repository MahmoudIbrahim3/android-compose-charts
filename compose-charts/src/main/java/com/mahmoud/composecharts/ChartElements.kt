package com.mahmoud.composecharts

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.mahmoud.composecharts.ui.theme.DefaultAxisLabelColor
import com.mahmoud.composecharts.ui.theme.DefaultAxisLabelFontSize
import com.mahmoud.composecharts.ui.theme.DefaultBarWidth
import com.mahmoud.composecharts.ui.theme.DefaultGridLinesColor

data class ChartElements(
    val showVerticalAxis: Boolean = true,
    val showHorizontalAxis: Boolean = true,
    val showDataLabels: Boolean = true,
    val showGridLines: Boolean = true,
    val gridLinesColor: Color = DefaultGridLinesColor,
    val labelColor: Color = DefaultAxisLabelColor,
    val showLegend: Boolean = true,
    val fontSize: TextUnit = DefaultAxisLabelFontSize,
    val barWidth: Dp = DefaultBarWidth,
)

//enum class Position(val position: Int) {
//    NON(0),
//    TOP(1),
//    BOTTOM(2),
//    LEFT(3),
//    RIGHT(4)
//}