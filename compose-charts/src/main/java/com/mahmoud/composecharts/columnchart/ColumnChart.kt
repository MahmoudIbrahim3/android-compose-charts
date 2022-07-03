package com.mahmoud.composecharts.columnchart

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahmoud.composecharts.ChartElements
import com.mahmoud.composecharts.dpToPx
import com.mahmoud.composecharts.textUnitToPx
import com.mahmoud.composecharts.ui.theme.*
import kotlin.math.max
import kotlin.math.pow

@Composable
fun ColumnChart(
    modifier: Modifier = Modifier
        .padding(top = 16.dp, bottom = 16.dp),
    seriesData: List<SeriesData>,
    categories: List<String>,
    chartElements: ChartElements = ChartElements()
) {
    val verticalAxisData = findVerticalAxisData(seriesData)
    var verticalAxisLength: Float? = null

    val mModifier = modifier.aspectRatio(1f)

    var bottomAreaHeight = textUnitToPx(chartElements.fontSize)
    if(chartElements.showLegend)
        bottomAreaHeight+= dpToPx(chartElements.barWidth)

//    verticalAxisLength = findVerticalAxisLength(chartElements.fontSize.toPx(), size.height)

    val fontSizeInPx = textUnitToPx(chartElements.fontSize)
    val leftAreaWidth = convertStringLengthIntoPx(
        verticalAxisData[verticalAxisData.size - 1].toString(),
        fontSizeInPx
    )

    drawCharsElements(
        mModifier,
        chartElements,
        verticalAxisData,
        bottomAreaHeight,
        leftAreaWidth,
        fontSizeInPx,
        categories,
        seriesData
    )

    drawColumns(
        mModifier,
        chartElements,
        seriesData,
        verticalAxisData[verticalAxisData.size - 1],
        bottomAreaHeight,
        leftAreaWidth
    )
}

fun convertStringLengthIntoPx(text: String, fontSizeInPx: Float) =
    (text.length * fontSizeInPx.div(1.75)).toInt()

@Composable
fun drawColumns(
    modifier: Modifier,
    chartElements: ChartElements,
    seriesData: List<SeriesData>,
    maxAxisValue: Float,
    bottomAreaHeight: Float,
    leftAreaWidth: Int,
) {
    Canvas(
        modifier = modifier.aspectRatio(1f)
    ) {
        val verticalAxisLength = findAxisHeight(size.height, bottomAreaHeight)

        val period = (size.width - leftAreaWidth) / findMaxSeriesLength(seriesData)

        var start = 0f
        val barWidth = chartElements.barWidth.toPx()

        for (seriesIndex in seriesData.indices) {
            start = barWidth.times(seriesIndex) +
                    period.div(2) - seriesData.size.times(barWidth).div(2) // To makes the columns centered in the view
            for (index in seriesData[seriesIndex].series.indices) {
                val series = seriesData[seriesIndex]

                val x = leftAreaWidth + period.times(index) + start

                val barHeightPercentage = (series.series[index] / maxAxisValue)
                val barHeightInPixel = barHeightPercentage * verticalAxisLength

                // Draw vertical bar
                drawRect(
                    color = series.color,
                    topLeft = Offset(x, verticalAxisLength - barHeightInPixel),
                    size = Size(barWidth, barHeightInPixel)
                )
            }
        }
    }
}

fun findMaxSeriesLength(seriesData: List<SeriesData>): Int {
    var maxLength = Int.MIN_VALUE
    seriesData.forEach {
        if(it.series.size > maxLength)
            maxLength = it.series.size
    }
    return maxLength
}

@Composable
fun drawCharsElements(
    modifier: Modifier?,
    chartElements: ChartElements,
    verticalAxisData: List<Float>,
    bottomAreaHeight: Float,
    leftAreaWidth: Int,
    fontSizeInPx: Float,
    categories: List<String>,
    seriesData: List<SeriesData>,
) {
    val axisThicknessPx = dpToPx(DefaultAxisThickness)

    Canvas(
        modifier = modifier!!.aspectRatio(1f)
    ) {
        val verticalAxisLength = findAxisHeight(size.height, bottomAreaHeight)

        val horizontalAxisLength = size.width - leftAreaWidth
        val distanceBetweenVerticalAxisValues = (verticalAxisLength / (verticalAxisData.size - 1))

        // Draw vertical axis values & horizontal lines
        for (index in verticalAxisData.indices) {

            val x = (leftAreaWidth / 2).toFloat()
            val y = verticalAxisLength - (distanceBetweenVerticalAxisValues).times(index)

            // Draw vertical axis value
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    verticalAxisData[index].toString(),
                    x,
                    y + fontSizeInPx / 2,
                    Paint().apply {
                        textSize = fontSizeInPx
                        color = chartElements.labelColor.toArgb()
                        textAlign = Paint.Align.CENTER
                    }
                )
            }

            // Draw horizontal line
            if (chartElements.showGridLines)
                drawRect(
                    color = chartElements.gridLinesColor,
                    topLeft = Offset(leftAreaWidth.toFloat(), y),
                    size = Size(horizontalAxisLength, axisThicknessPx)
                )
        }

        // Draw horizontal axis labels
        val period = (size.width - leftAreaWidth) / categories.size
        val start = period.div(2) + leftAreaWidth
        val y = verticalAxisLength + fontSizeInPx.times(1.2).toInt()

        for (index in categories.indices) {
            val x = start + period.times(index)
            if (categories[index].isNotEmpty()) {
                drawContext.canvas.nativeCanvas.apply {
                    drawText(
                        categories[index],
                        x,
                        y,
                        Paint().apply {
                            textSize = fontSizeInPx
                            color = chartElements.labelColor.toArgb()
                            textAlign = Paint.Align.CENTER
                        }
                    )
                }
            }
        }

        // Draw legends
        val barWidth = chartElements.barWidth.toPx()
        val legendFontSizePx = fontSizeInPx.div(1.5f)

        var legendWidth = 0.0f
        for (series in seriesData.iterator()) {
            val stringLength = convertStringLengthIntoPx(series.seriesName, legendFontSizePx)
            legendWidth += barWidth + stringLength.div(2) + stringLength.div(2) + barWidth
        }
        val startXOfLegend = (size.width - legendWidth) / 2

        var legendX = startXOfLegend + barWidth
        val legendY = size.height

        for (series in seriesData.iterator()) {

            drawRect(
                color = series.color,
                topLeft = Offset(legendX, legendY),
                size = Size(barWidth, barWidth)
            )

            val stringLength = convertStringLengthIntoPx(series.seriesName, legendFontSizePx)
            legendX += barWidth + stringLength.div(2)

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    series.seriesName,
                    legendX,
                    legendY + barWidth.div(1.2f),
                    Paint().apply {
                        textSize = legendFontSizePx
                        color = chartElements.labelColor.toArgb()
                        textAlign = Paint.Align.CENTER
                    }
                )
            }

            legendX += stringLength.div(2) + barWidth
        }
    }
}

fun findAxisHeight(height: Float, bottomAreaHeight: Float): Float {
    return height - bottomAreaHeight
}

@Composable
fun findVerticalAxisData(seriesData: List<SeriesData>): List<Float> {
    val data = ArrayList<Float>()
    var maxValue = 0.0f
    for(series in seriesData.iterator()) {
        val thisMax = (series.series.maxOrNull() ?: 0.0f)
        if(maxValue < thisMax)
            maxValue = thisMax
    }

    val increment = maxValue / 6
    val digitsLengthTimesTen = 10.0.pow(increment.toInt().toString().length - 1)
    val remainingAfterModulus = (increment % digitsLengthTimesTen)

    val axisIncrement = if(remainingAfterModulus >= digitsLengthTimesTen.div(2))
        (increment - remainingAfterModulus) + digitsLengthTimesTen
    else
        (increment - remainingAfterModulus)

    for(i in 0..6)
        data.add((axisIncrement * i).toFloat())

    return data
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AndroidComposeChartsTheme {
        val seriesData = listOf(
            SeriesData(listOf(4.5f, 2.5f, 3f), Color.Blue, "Series1"),
            SeriesData(listOf(5f, 2f, 3f, 12f), Color.Red, "Series2"),
            SeriesData(listOf(2f, 4f, 6f), Color.Green, "Series3"),
            SeriesData(listOf(2f, 4f, 6f, 8f), Color.Black, "Series4"),
        )

        ColumnChart(
            seriesData = seriesData,
            categories = listOf("C1", "C2", "C3", "C4")
        )
    }
}