package com.mahmoud.composechartssamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoud.composecharts.barchart.BarChart
import com.mahmoud.composecharts.linechart.LineChart
import com.mahmoud.composechartssamples.data.*
import com.mahmoud.composechartssamples.ui.theme.AndroidComposeChartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeChartsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    BarChart(
                        barChartData = barChartData,
                        verticalAxisValues = verticalAxisValues,
                    )
                }
            }
        }
    }
}

/**
 * Bar Chart Previews
 */
@Preview(showBackground = true)
@Composable
private fun Preview_BarChart_Default() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData,
            verticalAxisValues = verticalAxisValues
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_BarChart_2Bars() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData2,
            verticalAxisValues = verticalAxisValues
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_BarChart_CustomAttributes() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData3,
            verticalAxisValues = verticalAxisValues,
            axisColor = Color.Red,
            verticalAxisLabelColor = Color.Blue,
            verticalAxisLabelFontSize = 20.sp,
            horizontalAxisLabelColor = Color.Magenta,
            horizontalAxisLabelFontSize = 24.sp,
            paddingBetweenBars = 16.dp,
            isShowVerticalAxis = true,
            isShowHorizontalLines = false,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Default_BarChart_10BarsWithNoLabels() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData4,
            verticalAxisValues = verticalAxisValues2,
        )
    }
}

/**
 * Line Chart Previews
 */
@Preview(showBackground = true)
@Composable
private fun Preview_LineChart_Default() {
    AndroidComposeChartsTheme {
        LineChart(
            lineChartData = lineChartData,
            verticalAxisValues = verticalAxisValues
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_LineChart_CustomAttributes() {
    AndroidComposeChartsTheme {
        LineChart(
            lineChartData = lineChartData2,
            verticalAxisValues = verticalAxisValues2,
            lineColor = Color.Black,
            strokeWidth = 7.dp,
            axisColor = Color(0xFFC32A33),
            verticalAxisLabelColor = Color.Blue,
            verticalAxisLabelFontSize = 20.sp,
            horizontalAxisLabelColor = Color.Red,
            horizontalAxisLabelFontSize = 20.sp,
            isShowVerticalAxis = true,
            isShowHorizontalLines = true,
        )
    }
}
