plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.garciafrancisco.data"
    compileSdk = Versions.SDK.compileSdk

    defaultConfig {
        minSdk = Versions.SDK.minSdk
        targetSdk = Versions.SDK.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Versions.Java.javaVersion
        targetCompatibility = Versions.Java.javaVersion
    }
    kotlinOptions {
        jvmTarget = Versions.Java.jvmTarget
    }
}

dependencies {
    //Logging
    implementation(Dependencies.Timber.timber)
    // Retrofit
    implementation(Dependencies.HttpClient.retrofit)
    implementation(Dependencies.HttpClient.moshiConverter)
    implementation(Dependencies.HttpClient.moshiKotlin)
    implementation(Dependencies.HttpClient.loggingInterceptor)
    // Testing
    testImplementation(Dependencies.Testing.junit)
}