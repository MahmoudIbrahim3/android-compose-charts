package com.mahmoud.composecharts.barchart

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mahmoud.composecharts.ui.theme.AndroidComposeChartsTheme

@Composable
fun BarChart(
    modifier: Modifier? = Modifier
        .padding(top = 16.dp, bottom = 16.dp),
    barChartData: List<BarChartEntity>,
    verticalAxisValues: List<Float>,
    axisColor: Color = Color(0xFFBFC0BF),
    horizontalAxisLabelColor: Color = Color(0xFF5B5E5B),
    horizontalAxisLabelFontSize: TextUnit = 16.sp,
    verticalAxisLabelColor: Color = Color(0xFF5B5E5B),
    verticalAxisLabelFontSize: TextUnit = 16.sp,
    paddingBetweenBars: Dp = 12.dp,
    isShowVerticalAxis: Boolean = false,
    isShowHorizontalLines: Boolean = true,
) {
    val paddingBetweenBarsPx = dpToPx(paddingBetweenBars)

    Canvas(
        modifier = modifier!!.aspectRatio(1f)
    ) {
        val axisThickness = 5.0f

        val bottomAreaHeight = horizontalAxisLabelFontSize.toPx()
        val leftAreaWidth = verticalAxisValues[verticalAxisValues.size - 1].toString().length * 25

        val verticalAxisLength = (size.height - bottomAreaHeight)
        val horizontalAxisLength = size.width - leftAreaWidth

        val distanceBetweenVerticalAxisValues = (verticalAxisLength / (verticalAxisValues.size - 1))

        // Draw horizontal axis
        if(isShowHorizontalLines.not())
            drawRect(
                color = axisColor,
                topLeft = Offset(leftAreaWidth.toFloat(), verticalAxisLength),
                size = Size(horizontalAxisLength, axisThickness / 2)
            )

        // Draw vertical axis
        if(isShowVerticalAxis)
            drawRect(
                color = axisColor,
                topLeft = Offset(leftAreaWidth.toFloat(), 0.0f),
                size = Size(axisThickness, verticalAxisLength)
            )

        // Draw vertical axis values & horizontal lines
        for (index in verticalAxisValues.indices) {

            val x = (leftAreaWidth / 2).toFloat()
            val y = verticalAxisLength - (distanceBetweenVerticalAxisValues).times(index)

            // Draw vertical axis value
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    verticalAxisValues[index].toString(),
                    x,
                    y + verticalAxisLabelFontSize.toPx() / 2,
                    Paint().apply {
                        textSize = verticalAxisLabelFontSize.toPx()
                        color = verticalAxisLabelColor.toArgb()
                        textAlign = Paint.Align.CENTER
                    }
                )
            }

            // Draw horizontal line
            if (isShowHorizontalLines)
                drawRect(
                    color = axisColor,
                    topLeft = Offset(leftAreaWidth.toFloat(), y),
                    size = Size(horizontalAxisLength, axisThickness / 2)
                )
        }

        // Draw bars and it's labels
        val totalBarPadding = paddingBetweenBarsPx * (barChartData.size + 1)
        val barWidth =
            (drawContext.size.width - totalBarPadding - leftAreaWidth) / barChartData.size

        for (index in barChartData.indices) {
            val entity = barChartData[index]

            var x = (barWidth + paddingBetweenBarsPx) * index
            x += paddingBetweenBarsPx + leftAreaWidth

            val maxAxisValue = verticalAxisValues[verticalAxisValues.size - 1].toFloat()
            val barHeightPercentage = (entity.value / maxAxisValue)
            val barHeightInPixel = barHeightPercentage * verticalAxisLength

            // Draw vertical bar
            drawRect(
                color = entity.color,
                topLeft = Offset(x, verticalAxisLength - barHeightInPixel),
                size = Size(barWidth, barHeightInPixel)
            )

            // Draw horizontal axis label
            if (barChartData[index].label?.isNotEmpty() == true) {
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        barChartData[index].label!!,
                        x + barWidth.div(2),
                        verticalAxisLength + horizontalAxisLabelFontSize.toPx(),
                        Paint().apply {
                            textSize = bottomAreaHeight
                            color = horizontalAxisLabelColor.toArgb()
                            textAlign = Paint.Align.CENTER
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun dpToPx(value: Dp): Float = LocalDensity.current.run { value.toPx() }

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AndroidComposeChartsTheme {
        val barChartData = ArrayList<BarChartEntity>()
        barChartData.add(BarChartEntity(150.0f, Color(0xFF618A32), "1"))
        barChartData.add(BarChartEntity(450.0f, Color(0xFFC32A33), "2"))
        barChartData.add(BarChartEntity(300.0f, Color.Blue, "3"))
        barChartData.add(BarChartEntity(150.0f, Color.Cyan, "4"))
        barChartData.add(BarChartEntity(500.0f, Color.Magenta, "5"))

        BarChart(
            barChartData = barChartData,
            verticalAxisValues = listOf(0.0f, 100.0f, 200.0f, 300.0f, 400.0f, 500.0f),
//            axisColor = Color(0xFFA6A6A6),
//            verticalAxisLabelColor = Color(0xFFA6A6A6),
//            horizontalAxisLabelColor = Color(0xFF4F4F4F),
//            horizontalAxisLabelFontSize = 20.sp,
//            isShowVerticalAxis = false,
        )
    }
}