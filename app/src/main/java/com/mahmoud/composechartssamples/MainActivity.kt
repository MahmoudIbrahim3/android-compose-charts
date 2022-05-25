package com.mahmoud.composechartssamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mahmoud.composecharts.barchart.BarChart
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

@Preview(showBackground = true)
@Composable
private fun Preview_Default() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData,
            verticalAxisValues = verticalAxisValues
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_2Bars() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData2,
            verticalAxisValues = verticalAxisValues
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview_CustomAttributes() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData3,
            verticalAxisValues = verticalAxisValues,
            axisColor = Color.Red,
            verticalAxisLabelColor = Color.Blue,
            horizontalAxisLabelColor = Color.Magenta,
            horizontalAxisLabelFontSize = 20.sp,
            isShowVerticalAxis = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Default_10BarsWithNoLabels() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData4,
            verticalAxisValues = verticalAxisValues2,
        )
    }
}
