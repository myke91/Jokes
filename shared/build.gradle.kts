plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
}

android {
    compileSdk = AppConfig.Versions.compileSdk

    defaultConfig {
        minSdk = AppConfig.Versions.minSdk
        targetSdk = AppConfig.Versions.targetSdk

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
}