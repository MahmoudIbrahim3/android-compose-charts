# android-compose-charts

[![](https://jitpack.io/v/MahmoudIbrahim3/android-compose-charts.svg)](https://jitpack.io/#MahmoudIbrahim3/android-compose-charts)

This is an open source library used to draw charts in Android with [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAjwp7eUBhBeEiwAZbHwkYOGccOYl5HZsyYQm2SfdvvnWaoBuCkQXpjob2trtRVl4MbvkzMx9BoCHsoQAvD_BwE&gclsrc=aw.ds) with a simple and easy to use. Just couples of lines.

## Usage:
* Add this line to your project `gradle` file:

  ```kotlin
  maven { url 'https://jitpack.io' }
  ```

* Add this dependency to your app `gradle` file:

  ```kotlin
  implementation 'com.github.MahmoudIbrahim3:android-compose-charts:<latest-release>'
  ```

*****

### 1. Bar Chart:

  ```kotlin
  val barChartData = listOf(
    BarChartEntity(150.0f, Color(0xFF618A32), "1"),
    BarChartEntity(450.0f, Color(0xFFC32A33), "2"),
    BarChartEntity(300.0f, Color.Blue, "3"),
    BarChartEntity(150.0f, Color.Cyan, "4"),
    BarChartEntity(500.0f, Color.Magenta, "5")
  )

  val verticalAxisValues = listOf(0.0f, 100.0f, 200.0f, 300.0f, 400.0f, 500.0f)

  BarChart(
      barChartData = barChartData,
      verticalAxisValues = verticalAxisValues
  )
  ```
  
#### The following code snippet demonstrates a lot of attributes to custom and design your Bar Chart as you like:

  ```kotlin
  BarChart(
    barChartData = barChartData,
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
  ```
#### Bar Chart Samples:

![1](https://user-images.githubusercontent.com/17904163/170347680-088d2d6f-bc57-479f-9041-2bcc7f3d2341.PNG)
![2](https://user-images.githubusercontent.com/17904163/170347705-ed17018d-457b-46b6-9f94-402c174487a3.PNG)

*****

### 2. Line Chart:

```kotlin
val lineChartData = listOf(
    LineChartEntity(150.0f, "A"),
    LineChartEntity(250.0f, "B"),
    LineChartEntity(50.0f, "C"),
    LineChartEntity(300.0f, "D"),
    LineChartEntity(400.0f, "E")
)

LineChart(
    lineChartData = lineChartData,
    verticalAxisValues = verticalAxisValues
)
```

#### Custom attributes:

```kotlin
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
```

#### Line Chart Samples:

###### Default line chart
![LineChart1](https://user-images.githubusercontent.com/17904163/172702483-c28b2b2c-6c1f-4db0-81a4-12ff865d6b7c.png)

###### Custom line chart
![LineChart2](https://user-images.githubusercontent.com/17904163/172702590-2d1f58ee-a99c-4564-8515-14f931dd4d78.PNG)

*****

### 3. Column Chart:

```kotlin
val columnChartSeriesData = listOf(
    SeriesData(listOf(4f, 2.5f, 3.5f, 4.5f), Color(0xff4472c4), "Series1"),
    SeriesData(listOf(2.5f, 4.5f, 1.5f, 3f), Color(0xffed7d31), "Series3"),
    SeriesData(listOf(2.0f, 2f, 3f, 5f), Color(0xFF8BC34A), "Series3"),
)

val columnChartCategoriesData = listOf("Cat1", "Cat2", "Cat3", "Cat4")

ColumnChart(
    seriesData = columnChartSeriesData,
    categories = columnChartCategoriesData,
)
```

#### Custom attributes:

```kotlin
val columnChartSeriesData2 = listOf(
    SeriesData(listOf(4f, 3.5f), Color.Red, "Series1"),
    SeriesData(listOf(1.5f, 4.5f), Color.Green, "Series2")
)

val columnChartCategoriesData2 = listOf("Category1", "Category2")

ColumnChart(
    modifier = Modifier.padding(start = 4.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
    seriesData = columnChartSeriesData2,
    categories = columnChartCategoriesData2,
    chartElements = ChartElements(
        showVerticalLine = true,
        showGridLines = false,
        showHorizontalLabels = false,
        showLegend = false,
        gridLinesColor = Color.DarkGray,
        labelColor = Color.Black,
        barWidth = 40.dp,
        legendWidth = 6.dp,
        fontSize = 16.sp
    )
)
```


#### Column Chart Samples:

###### Default column chart
![ColumnChart1](https://user-images.githubusercontent.com/17904163/178757019-dff11122-fc28-4208-9798-5c4e37fe79ca.png)

###### Custom column chart
![ColumnChart2](https://user-images.githubusercontent.com/17904163/178757013-669f818b-b70e-490e-a81f-1a0ffbdcf627.png)

*****

#### Feel free to fork the repo and make any changes or suggestions you see and make a pull request.

#### Wait a lot of other types of charts using compose, coming soon Insha-Allah :)
