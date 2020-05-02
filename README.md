# SpaceX

![Snapshot](https://i.imgur.com/Jrhv4W9l.png) ![Snapshot](https://i.imgur.com/sRD6zoAl.png)

# Used components/libraries/design patterns:

- 100% Kotlin
- Kotlin Coroutines(instead of RxJava and Java threads)
- Dagger2
- MVVM
- Master/Detail flow
- View binding
- Livedata
- Retrofit
- OkHttp logging interceptor
- Leakcanary by Square(To catch memory leaks)
- Picasso
- CardView
- Swipte to refresh
- Timber
- Recyclerview
- Espresso

# Some notes:

- SpaceX API supports sorting. Instead of sorting the data in Android device, i ask API to return sorted data to not consume android processor power.
- Configuration changes are handled gracefully. When we rotate the device there will not be any API calls. Data will be simply returned from viewmodel.
- Http logging is enabled only in debug mode.
- I use "Region - endRegion" to make code cleaner.
- Almost all libraries/classes are injected by Dagger to classes.
- Timber is configured with custom TimberLineNumberDebugTree which provides clickable logs to navigate developer to the point where log was generated.
- I tried to document all classes and necessary methods with KDoc.
- LeakCanary helped me a lot to catch memory leaks and correct memory usage. It shows notifications when LeakCanary's object watcher detects leaks.


# Some scenarios:
- If GET request fails, there will be an error message with a try again button.
  
  
# Tests:
- Main Activity Test
  -  Starts the application and checks if recyclerview has some data. It ensures that we are able to do an API call and get response.


# Improve ideas:
- SpaceX API supports limit/offset. Instead of getting all the data from API with one call, we can implement endless scrolling list behavior.
- SpaceX API supports "Optional Ouput Control Querystrings". To populate recyclerview, we can get the minimum required data and when user clicks a row to see details of launch, we can make an additional call to get details of the launch.
- Implement crashlytic.
- Implement navigation components.
- Create a common ProgressDialog and inject with dagger wherever needed.

# Tasks before publishing the app:
- Run full lint inspection(should be done before every commit).
- Run static code analysis.
- Enable ProGuard.
- Use firebase test lab.

