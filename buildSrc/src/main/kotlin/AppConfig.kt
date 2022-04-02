import AppConfig.Versions.versionMajor
import AppConfig.Versions.versionMinor
import AppConfig.Versions.versionPatch

object AppConfig {
    const val applicationId = "com.lillydoo"
    const val testInstrumentationRunner = "com.lillydoo.MockTestRunner"

    object Versions {
        const val compileSdk = 31
        const val minSdk = 23
        const val targetSdk = 31

        internal const val versionMajor = 0
        internal const val versionMinor = 0
        internal const val versionPatch = 1
    }

    val versionCode
        get() = versionMajor * 1000000 + versionMinor * 10000 + versionPatch * 100

    val versionName
        get() = "${versionMajor}.${versionMinor}.${versionPatch}"
}