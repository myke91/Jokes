object Libs {


    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        }
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Facebook {
        const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    }

    object AndroidX {
        const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"


        object Navigation {
            const val fragment =
                "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
            const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        }

        object Lifecycle {
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        }

        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
        const val gsonConverter =
            "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Version}"
        const val moshiConverter =
            "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
    }

    object IdlingResource {
        const val idlingResource =
            "com.jakewharton.espresso:okhttp3-idling-resource:${Versions.jwIdlingResourceVersion}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Moshi {
        const val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
        const val moshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.moshiVersion}"
        const val moshiKotlinCodegen =
            "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
    }


    object Test {
        object MockitoKotlin {
            const val kotlin =
                "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
        }

        object Mockito {
            const val inline = "org.mockito:mockito-inline:${Versions.mockito}"
            const val android = "org.mockito:mockito-android:${Versions.mockito}"
        }

        object Kotlin {
            object Coroutine {
                const val junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.coroutines}"
                const val test =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
            }
        }

        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp3Version}"
        const val core = "androidx.test:core:${Versions.test}"
        const val runner = "androidx.test:runner:${Versions.test}"
        const val rules = "androidx.test:rules:${Versions.test}"
        const val ext = "androidx.test.ext:junit:${Versions.ext}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val barista = "com.adevinta.android:barista:${Versions.barista}"
        const val junit = "junit:junit:${Versions.junit}"
        const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    }
}