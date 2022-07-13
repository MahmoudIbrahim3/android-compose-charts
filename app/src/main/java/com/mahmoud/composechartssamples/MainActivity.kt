package com.mahmoud.composechartssamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoud.composecharts.ChartElements
import com.mahmoud.composecharts.barchart.BarChart
import com.mahmoud.composecharts.columnchart.ColumnChart
import com.mahmoud.composecharts.linechart.LineChart
import com.mahmoud.composechartssamples.data.*
import com.mahmoud.composechartssamples.ui.theme.AndroidComposeChartsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeChartsTheme {
                LazyColumn() {
                    item {
                        BarChart(
                            barChartData = barChartData,
                            verticalAxisValues = verticalAxisValues,
                        )
                    }

                    item {
                        ColumnChart(
                            seriesData = columnChartSeriesData,
                            categories = columnChartCategoriesData,
                        )
                    }
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
            lineColor = Color.Red,
            strokeWidth = 6.dp,
            axisColor = Color(0xF0E47A8A),
            verticalAxisLabelColor = Color.Blue,
            verticalAxisLabelFontSize = 20.sp,
            horizontalAxisLabelColor = Color.Black,
            horizontalAxisLabelFontSize = 20.sp,
            isShowVerticalAxis = true,
            isShowHorizontalLines = true,
        )
    }
}

/**
 * Column Chart Previews
 */
@Preview(showBackground = true)
@Composable
private fun Preview_ColumnChart_Default() {
    AndroidComposeChartsTheme {
        ColumnChart(
            seriesData = columnChartSeriesData,
            categories = columnChartCategoriesData,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_ColumnChart_CustomAttributes() {
    AndroidComposeChartsTheme {
        ColumnChart(
            modifier = Modifier.padding(start = 4.dp, end = 8.dp, top = 16.dp, bottom = 8.dp),
            seriesData = columnChartSeriesData2,
            categories = columnChartCategoriesData2,
            chartElements = ChartElements(
                showVerticalLine = true,
                showGridLines = true,
                showHorizontalLabels = false,
                showLegend = false,
                gridLinesColor = Color.DarkGray,
                labelColor = Color.Black,
                barWidth = 40.dp,
                legendWidth = 6.dp,
                fontSize = 16.sp
            )
        )
    }
}