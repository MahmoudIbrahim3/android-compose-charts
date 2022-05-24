package com.mahmoud.composechartssamples

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
private fun DefaultPreview() {
    AndroidComposeChartsTheme {
        BarChart(
            barChartData = barChartData,
            verticalAxisValues = verticalAxisValues,
//            axisColor = Color(0xFFA6A6A6),
//            verticalAxisLabelColor = Color(0xFFA6A6A6),
//            horizontalAxisLabelColor = Color(0xFF4F4F4F),
//            horizontalAxisLabelFontSize = 20.sp,
//            isShowVerticalAxis = false
        )
    }
}