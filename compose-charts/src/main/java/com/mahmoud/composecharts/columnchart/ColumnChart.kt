package com.mahmoud.composecharts.columnchart

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mahmoud.composecharts.ChartElements
import com.mahmoud.composecharts.dpToPx
import com.mahmoud.composecharts.textUnitToPx
import com.mahmoud.composecharts.ui.theme.*
import kotlin.math.pow

@Composable
fun ColumnChart(
    modifier: Modifier = Modifier
        .padding(top = 16.dp, bottom = 8.dp),
    seriesData: List<SeriesData>,
    categories: List<String>,
    chartElements: ChartElements = ChartElements()
) {
    val verticalAxisData = findVerticalAxisData(seriesData)

    val mModifier = modifier.aspectRatio(1f)

    var bottomAreaHeight = 0f

    if (chartElements.showHorizontalLabels)
        bottomAreaHeight += textUnitToPx(chartElements.fontSize) + dpToPx(8.dp)

    if (chartElements.showLegend)
        bottomAreaHeight += dpToPx(chartElements.legendWidth) + dpToPx(8.dp)

    val fontSizeInPx = textUnitToPx(chartElements.fontSize)
    val leftAreaWidth = convertStringLengthIntoPx(
        verticalAxisData[verticalAxisData.size - 1].toString(),
        fontSizeInPx
    )

    Canvas(
        modifier = mModifier
    ) {
        drawChartsElements(
            chartElements,
            verticalAxisData,
            bottomAreaHeight,
            leftAreaWidth,
            fontSizeInPx,
            categories,
            seriesData
        )

        drawColumns(
            chartElements,
            seriesData,
            verticalAxisData[verticalAxisData.size - 1],
            bottomAreaHeight,
            leftAreaWidth
        )
    }
}

private fun convertStringLengthIntoPx(text: String, fontSizeInPx: Float) =
    (text.length * fontSizeInPx.div(1.75)).toInt()

private fun DrawScope.drawColumns(
    chartElements: ChartElements,
    seriesData: List<SeriesData>,
    maxAxisValue: Float,
    bottomAreaHeight: Float,
    leftAreaWidth: Int,
) {

    val verticalAxisLength = findAxisHeight(size.height, bottomAreaHeight)

    val period = (size.width - leftAreaWidth) / findMaxSeriesLength(seriesData)

    var start: Float
    val barWidth = chartElements.barWidth.toPx()
    val spaceBetweenColumns = barWidth.div(5)

    for (seriesIndex in seriesData.indices) {
        start = leftAreaWidth +
                barWidth.times(seriesIndex) +
                spaceBetweenColumns.times(seriesIndex) +
                period.div(2) -
                seriesData.size.times(barWidth + spaceBetweenColumns).div(2) // To makes the columns centered in the view

        for (index in seriesData[seriesIndex].series.indices) {
            val series = seriesData[seriesIndex]

            val x = start + period.times(index)

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

private fun findMaxSeriesLength(seriesData: List<SeriesData>): Int {
    var maxLength = Int.MIN_VALUE
    seriesData.forEach {
        if(it.series.size > maxLength)
            maxLength = it.series.size
    }
    return maxLength
}

private fun DrawScope.drawChartsElements(
    chartElements: ChartElements,
    verticalAxisData: List<Float>,
    bottomAreaHeight: Float,
    leftAreaWidth: Int,
    fontSizeInPx: Float,
    categories: List<String>,
    seriesData: List<SeriesData>,
) {
    val axisThicknessPx = DefaultAxisThickness.toPx()

    val verticalAxisLength = findAxisHeight(size.height, bottomAreaHeight)

    val horizontalAxisLength = size.width - leftAreaWidth
    val distanceBetweenVerticalAxisValues = (verticalAxisLength / (verticalAxisData.size - 1))

    // Draw vertical line
    if (chartElements.showVerticalLine)
        drawRect(
            color = chartElements.gridLinesColor,
            topLeft = Offset(leftAreaWidth.toFloat(), 0.0f),
            size = Size(axisThicknessPx, verticalAxisLength)
        )

    // Draw horizontal line
    if (chartElements.showGridLines.not())
        drawRect(
            color = chartElements.gridLinesColor,
            topLeft = Offset(leftAreaWidth.toFloat(), verticalAxisLength),
            size = Size(horizontalAxisLength, axisThicknessPx)
        )

    // Draw vertical axis values & horizontal gridlines
    for (index in verticalAxisData.indices) {

        val x = (leftAreaWidth / 2).toFloat()
        val y = verticalAxisLength - (distanceBetweenVerticalAxisValues).times(index)

        // Vertical axis value
        drawContext.canvas.nativeCanvas.apply {
            drawText(
                verticalAxisData[index].toString(),
                x,
                y + fontSizeInPx / 2.7f,
                Paint().apply {
                    textSize = fontSizeInPx
                    color = chartElements.labelColor.toArgb()
                    textAlign = Paint.Align.CENTER
                }
            )
        }

        // Horizontal line
        if (chartElements.showGridLines)
            drawRect(
                color = chartElements.gridLinesColor,
                topLeft = Offset(leftAreaWidth.toFloat(), y),
                size = Size(horizontalAxisLength, axisThicknessPx)
            )
    }

    // Draw horizontal axis labels
    if (chartElements.showHorizontalLabels) {
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
    }

    // Draw legends
    if (chartElements.showLegend) {
        val legendWidth = chartElements.legendWidth.toPx()
        val legendFontSizePx = fontSizeInPx.div(1.5f)

        var legendSectionWidth = 0.0f
        for (series in seriesData.iterator()) {
            val stringLength = convertStringLengthIntoPx(series.seriesName, legendFontSizePx)
            legendSectionWidth += legendWidth + stringLength.div(2) + stringLength.div(2) + legendWidth
        }
        val startXOfLegend = (size.width - legendSectionWidth) / 2

        var legendX = startXOfLegend + legendWidth
        val legendY = size.height - legendWidth

        for (series in seriesData.iterator()) {

            drawRect(
                color = series.color,
                topLeft = Offset(legendX, legendY),
                size = Size(legendWidth, legendWidth)
            )

            val stringLength = convertStringLengthIntoPx(series.seriesName, legendFontSizePx)
            legendX += legendWidth + stringLength.div(2)

            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    series.seriesName,
                    legendX,
                    legendY + legendFontSizePx.div(3) + legendWidth.div(2),
                    Paint().apply {
                        textSize = legendFontSizePx
                        color = chartElements.labelColor.toArgb()
                        textAlign = Paint.Align.CENTER
                    }
                )
            }

            legendX += stringLength.div(2) + legendWidth
        }
    }
}

private fun findAxisHeight(height: Float, bottomAreaHeight: Float): Float {
    return height - bottomAreaHeight
}

@Composable
private fun findVerticalAxisData(seriesData: List<SeriesData>): List<Float> {
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

@Preview(showBackground = true, heightDp = 1080)
@Composable
private fun DefaultPreview() {
    AndroidComposeChartsTheme {
        val seriesData = listOf(
            SeriesData(listOf(4f, 2.5f, 3.5f, 4.5f), Color(0xff4472c4), "Series1"),
            SeriesData(listOf(2.5f, 4.5f, 1.5f, 3f), Color(0xffed7d31), "Series3"),
            SeriesData(listOf(2.0f, 2f, 3f, 5f), Color.Gray, "Series4"),
        )

        ColumnChart(
            seriesData = seriesData,
            categories = listOf("C1", "C2", "C3", "C4"),
            chartElements = ChartElements(
//                showVerticalLine = true,
//                showGridLines = true,
//                showHorizontalLabels = true,
//                showLegend = false,
//                gridLinesColor = Color.Green,
//                labelColor = Color.Blue,
//                barWidth = 40.dp,
//                legendWidth = 20.dp,
//                fontSize = 30.sp
            )
        )
    }
}