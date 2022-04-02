plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    compileSdk = AppConfig.Versions.compileSdk

    defaultConfig {
        minSdk = AppConfig.Versions.minSdk
        targetSdk = AppConfig.Versions.targetSdk

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BASE_URL", "\"https://v2.jokeapi.dev/\"")

    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(mapOf("path" to ":shared")))

    //AndroidX
    implementation(Libs.AndroidX.cardView)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.Google.material)
    implementation(Libs.AndroidX.recyclerView)
    implementation(Libs.AndroidX.swipeRefresh)

    //lifecycle components
    implementation(Libs.AndroidX.Lifecycle.viewmodel)
    implementation(Libs.AndroidX.Lifecycle.runtime)

    //navigation component
    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.Navigation.ui)

    //retrofit
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.gsonConverter)
    implementation(Libs.Retrofit.loggingInterceptor)
    implementation(Libs.Retrofit.moshiConverter)

    //idling resource
    implementation(Libs.IdlingResource.idlingResource)

    //hilt
    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.compiler)

    //moshi
    implementation(Libs.Moshi.moshi)
    implementation(Libs.Moshi.moshiKotlin)
    implementation(Libs.Moshi.moshiAdapters)
    kapt(Libs.Moshi.moshiKotlinCodegen)

    //coroutine
    implementation(Libs.Kotlin.Coroutines.core)
    implementation(Libs.Kotlin.Coroutines.android)

    //shimmer
    implementation(Libs.Facebook.shimmer)

    testImplementation(Libs.Test.junit)
    testImplementation(Libs.Test.Mockito.inline)
    testImplementation(Libs.Test.Mockito.android)
    testImplementation(Libs.Test.Kotlin.Coroutine.test)
    testImplementation(Libs.Test.Kotlin.Coroutine.junit)
    testImplementation(Libs.Test.MockitoKotlin.kotlin)
    testImplementation(Libs.Test.turbine)
    testImplementation(Libs.IdlingResource.idlingResource)
}