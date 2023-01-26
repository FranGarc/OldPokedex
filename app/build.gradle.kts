plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.garciafrancisco.pokedex"
    compileSdk = Versions.SDK.compileSdk

    defaultConfig {
        applicationId = "com.garciafrancisco.pokedex"
        minSdk = Versions.SDK.minSdk
        targetSdk = Versions.SDK.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(path = ":domain"))

    implementation(Dependencies.Android.core)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.Views.recylerView)
    implementation(Dependencies.Android.Views.constraintLayout)

    // Navigation
    implementation(Dependencies.Android.Navigation.navigationFragment)
    implementation(Dependencies.Android.Navigation.navigationUi)


    // Material
    implementation(Dependencies.Android.UX.material)

    // Palette
//    implementation 'com.androidx.support:palette-v7:28.0.0'
    // Glide
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.compiler)

    // Architecture
    implementation(Dependencies.Android.LifeCycle.liveData)
    implementation(Dependencies.Android.LifeCycle.viewModel)
    implementation(Dependencies.Android.LifeCycle.runtime)


    // Coroutines
    implementation(Dependencies.Coroutines.core)
    implementation(Dependencies.Coroutines.android)

    // Retrofit
    implementation(Dependencies.HttpClient.retrofit)
    implementation(Dependencies.HttpClient.moshiConverter)
    implementation(Dependencies.HttpClient.moshiKotlin)
    implementation(Dependencies.HttpClient.loggingInterceptor)

    //Logging
    implementation(Dependencies.Timber.timber)
    // Testing
    testImplementation(Dependencies.Testing.junit)
    androidTestImplementation(Dependencies.Testing.AndroidTesting.junit)
    androidTestImplementation(Dependencies.Testing.AndroidTesting.espressoCore)

}