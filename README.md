SUMMARY
=======
This project was refactored to use MVVM with Clean Architecture.

The project was modularized for the benefits of speeding up builds,
enabling on demand delivery (when necessary), simplifying development, Reusing modules across apps
scaling development teams, enabling refactoring, simplifying test automation etc. The modules include shared module and jokes feature module

A Data Transfer Object (DTO) and a mapper is used to map the data received from the api
to a ui consumable object.

A navigation graph is used for all the benefits stated in the official [documentation](https://developer.android.com/guide/navigation)

[Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is used
for dependency injection to prevent managing dependencies manually as the application scales since that becomes difficult.


[Shimmer effect](https://facebook.github.io/shimmer-android/) is used to indicate loading while the API call occurs

Kotlin DSL is being used for managing dependencies for its with an enhanced editing experience in supported IDEs, content assistance, ease of refactoring,
etc. And this makes it generally easier to manage applications with a lot of dependencies

[Barista](https://github.com/AdevintaSpain/Barista) was added for ui tests to help simplify view assertions and make UI tests more predictable.

[Turbine](https://github.com/cashapp/turbine) was introduced for testing kotlin flows
