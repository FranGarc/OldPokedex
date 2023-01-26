import org.gradle.api.JavaVersion

object Versions {

    const val androidCore = "1.9.0"
    const val appCompat = "1.5.1"
    const val lifeCycle = "2.5.1"

    const val recyclerview = "1.2.1"
    const val constraintLayout = "2.1.4"

    const val material = "1.7.0"

    const val navigation = "2.5.3"

    const val coroutineCore = "1.5.2"
    const val coroutineAndroid = "1.6.1"


    const val retrofit = "2.9.0"
    const val moshiKotlin = "1.9.3"
    const val logginInterceptor = "4.10.0"


    const val glide = "4.14.2"

    const val timber = "5.0.1"

    object Testing {
        const val junit = "4.13.2"

        object AndroidTesting {
            val junit = "1.1.4"
            val espresso = "3.5.0"
        }
    }


    object Java {
        val javaVersion = JavaVersion.VERSION_11
        const val jvmTarget = "11"
    }


    object SDK {
        const val compileSdk = 33
        const val minSdk = 28
        const val targetSdk = 33
    }

    object Plugins {
        const val kotlin = "1.7.10"
        const val android = "7.3.0"
        const val gradlePlugin = "4.2.1"

        const val jUnit = "4.12"
    }

}