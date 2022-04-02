plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.hilt)
    id("kotlin-android")
}


android {
    compileSdk = AppConfig.Versions.compileSdk
    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.Versions.minSdk
        targetSdk = AppConfig.Versions.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.testInstrumentationRunner
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



    packagingOptions {
        resources.excludes.add("META-INF/com.android.tools/proguard/coroutines.pro")
    }
}

dependencies {

    implementation(project(mapOf("path" to ":feature:jokes")))
    implementation(Libs.Kotlin.stdlib)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.Navigation.fragment)
    implementation(Libs.AndroidX.splashScreen)

    //hilt
    implementation(Libs.Hilt.hilt)
    kapt(Libs.Hilt.compiler)


    androidTestImplementation(Libs.Test.rules)
    androidTestImplementation(Libs.Test.ext)
    androidTestImplementation(Libs.Test.Mockito.android)
    androidTestImplementation(Libs.Test.espressoCore)
    androidTestImplementation(Libs.IdlingResource.idlingResource)
    androidTestImplementation(Libs.Test.mockWebServer)
    androidTestImplementation(Libs.Test.barista) {
        exclude("org.jetbrains", "kotlin") // Only if you already use Kotlin in your project
    }
}
