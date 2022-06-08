package com.mahmoud.composecharts.linechart

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
import com.mahmoud.composecharts.dpToPx
import com.mahmoud.composecharts.ui.theme.*

@Composable
fun LineChart(
    modifier: Modifier? = Modifier
        .padding(top = 16.dp, bottom = 16.dp),
    lineChartData: List<LineChartEntity>,
    verticalAxisValues: List<Float>,
    axisColor: Color = DefaultAxisColor,
    horizontalAxisLabelColor: Color = DefaultAxisLabelColor,
    horizontalAxisLabelFontSize: TextUnit = DefaultAxisLabelFontSize,
    verticalAxisLabelColor: Color = DefaultAxisLabelColor,
    verticalAxisLabelFontSize: TextUnit = DefaultAxisLabelFontSize,
    isShowVerticalAxis: Boolean = false,
    isShowHorizontalLines: Boolean = true,
    strokeWidth: Dp = 4.dp,
    lineColor: Color = Color.Blue,
) {
    val strokeWidthPx = dpToPx(strokeWidth)
    val axisThicknessPx = dpToPx(DefaultAxisThickness)

    Canvas(
        modifier = modifier!!.aspectRatio(1f)
    ) {

        val bottomAreaHeight = horizontalAxisLabelFontSize.toPx()
        val leftAreaWidth =
            (verticalAxisValues[verticalAxisValues.size - 1].toString().length * verticalAxisLabelFontSize.toPx().div(1.75)).toInt()

        val verticalAxisLength = (size.height - bottomAreaHeight)
        val horizontalAxisLength = size.width - leftAreaWidth

        val distanceBetweenVerticalAxisValues = (verticalAxisLength / (verticalAxisValues.size - 1))

        // Draw horizontal axis
        if (isShowHorizontalLines.not())
            drawRect(
                color = axisColor,
                topLeft = Offset(leftAreaWidth.toFloat(), verticalAxisLength),
                size = Size(horizontalAxisLength, axisThicknessPx)
            )

        // Draw vertical axis
        if (isShowVerticalAxis)
            drawRect(
                color = axisColor,
                topLeft = Offset(leftAreaWidth.toFloat(), 0.0f),
                size = Size(axisThicknessPx, verticalAxisLength)
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
                    size = Size(horizontalAxisLength, axisThicknessPx)
                )
        }

        // Draw lines and it's labels
        val barWidth =
            (drawContext.size.width - leftAreaWidth) / lineChartData.size

        val maxAxisValue = verticalAxisValues[verticalAxisValues.size - 1]

        var previousOffset: Offset? = null

        for (index in lineChartData.indices) {
            val entity = lineChartData[index]

            // Draw line
            val currentOffset = calculateOffset(
                entity.value,
                index,
                maxAxisValue,
                barWidth,
                leftAreaWidth,
                verticalAxisLength
            )

            val end = Offset(currentOffset.x + barWidth.div(2), currentOffset.y)

            drawCircle(
                color = lineColor,
                center = end,
                radius = strokeWidthPx.times(1.5f)
            )

            if (previousOffset != null) {
                val start = Offset(previousOffset.x + barWidth.div(2), previousOffset.y)
                drawLine(
                    start = start,
                    end = end,
                    color = lineColor,
                    strokeWidth = strokeWidthPx
                )
            }

            previousOffset = currentOffset

            // Draw horizontal axis label
            if (lineChartData[index].label?.isNotEmpty() == true) {
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        lineChartData[index].label!!,
                        currentOffset.x + barWidth.div(2),
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

private fun calculateOffset(
    value: Float,
    index: Int,
    maxAxisValue: Float,
    barWidth: Float,
    leftAreaWidth: Int,
    verticalAxisLength: Float
): Offset {
    var x = barWidth * index
    x += leftAreaWidth

    val barHeightPercentage = (value / maxAxisValue)
    val barHeightInPixel = barHeightPercentage * verticalAxisLength
    val y = verticalAxisLength - barHeightInPixel

    return Offset(x, y)
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AndroidComposeChartsTheme {
        val lineChartData = ArrayList<LineChartEntity>()
        lineChartData.add(LineChartEntity(150.0f, "1"))
        lineChartData.add(LineChartEntity(450.0f, "2"))
        lineChartData.add(LineChartEntity(300.0f, "3"))
        lineChartData.add(LineChartEntity(200.0f, "4"))
        lineChartData.add(LineChartEntity(500.0f, "5"))

        LineChart(
            lineChartData = lineChartData,
            verticalAxisValues = listOf(0.0f, 100.0f, 200.0f, 300.0f, 400.0f, 500.0f),
//            axisColor = Color(0xFFA6A6A6),
//            verticalAxisLabelColor = Color(0xFFA6A6A6),
//            horizontalAxisLabelColor = Color(0xFF4F4F4F),
//            isShowVerticalAxis = false,
            verticalAxisLabelFontSize = 20.sp,
            horizontalAxisLabelFontSize = 30.sp,
            isShowVerticalAxis = true
        )
    }
}