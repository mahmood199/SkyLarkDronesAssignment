# ComposedWeather

Summary

A simple weather app that gets your location or you search for location and displays the forecast for that day and a few days after that. Also it shows various other metrics like humidity, temperature, rain and precipitation.


*API :* 
1. [OpenWeatherMap](https://open-meteo.com/en/docs) - To get the metrics for weather. Also this is free and provides a lot of parameters for weather.
2. [Nomatim](https://nominatim.openstreetmap.org/) - To get the location co-ordinates(latitude/longitude) as per user search. This is also free to use.


Reason for choosing mentioned API :
1. Both the APIs are free to use for small projects.
2. Wide audience of apps use this api. Hence its reliable.
3. Flexible - Many more fields can be requested from the same response body by adding to it. Hence avoiding duplicate fields across response classes.

# Pre-requisite 📝

In your `local.properties` you will need to add your Open Weather API key and copy the urls in.

```properties
  Just clone the repo and run.
  Make sure to use latest Anroid studio and Android Gradle Version > 8.0.0
```

*Environment*
- Built on A.S Iguana
- Gradle Version > 8
- Kotlin version >= 1.9.0


# Screenshots 📱

|                         Home(Celsius, Current Info, Hourly Forecast, 10 days Forecast)                          |
|:---------------------------------------------------------:|
| <img src="https://drive.google.com/file/d/11RcSgkwLNiz-X5c92YJyYMHulVhE7yj2/view" width="300px"> |


|                         Home(Celsius, Current Info, 10 days Forecast)                          |
|:---------------------------------------------------------:|
| <img src="https://drive.google.com/file/d/11Y6OQyfw2i7FvHWgawGdJuDA_rsmb4O4/view" width="300px"> |


|                         Home(Celsius, 10 days Forecast)                           |
|:---------------------------------------------------------:|
| <img src="https://drive.google.com/file/d/11_SYTd0Qu3GV1hk0ZZfreoOmoF2GSGyU/view" width="300px"> |


|                         Search(With Search Result)                          |
|:---------------------------------------------------------:|
| <img src="https://drive.google.com/file/d/11Y6OQyfw2i7FvHWgawGdJuDA_rsmb4O4/view" width="300px"> |


|                         Recording of the App Flow                        |
|:---------------------------------------------------------:|
https://drive.google.com/file/d/11Q_j8K_mQI8fsHnHib5gTmrk3UzfIj9I/view


# LICENSE

```
   Copyright 2023 David Odari

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
```

