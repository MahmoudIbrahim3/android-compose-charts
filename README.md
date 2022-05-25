# android-compose-charts

This is a library for drawing charts in Android with [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAjwp7eUBhBeEiwAZbHwkYOGccOYl5HZsyYQm2SfdvvnWaoBuCkQXpjob2trtRVl4MbvkzMx9BoCHsoQAvD_BwE&gclsrc=aw.ds) with a simple and easy to use. Just couples of lines.

## Usage:
* Add this line to your project `gradle` file:

  ```
  maven { url 'https://jitpack.io' }
  ```

* Add this dependency to your app `gradle` file:

  ```
  implementation 'com.github.MahmoudIbrahim3:android-compose-charts:0.0.5'
  ```

### 1. Bar Chart:

  ```
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
  
### Bar Chart Samples:

![1](https://user-images.githubusercontent.com/17904163/170347680-088d2d6f-bc57-479f-9041-2bcc7f3d2341.PNG)
![2](https://user-images.githubusercontent.com/17904163/170347705-ed17018d-457b-46b6-9f94-402c174487a3.PNG)

