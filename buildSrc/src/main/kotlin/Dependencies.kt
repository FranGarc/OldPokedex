object Dependencies {
    object Timber {
        val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }
    }

    object Plugins {
        object Android {
            val application by lazy { "com.android.application" }
            val library by lazy { "com.android.library" }
        }

        val kotlin by lazy { "com.android.library" }
    }

    object Android {
        val core by lazy { "androidx.core:core-ktx:${Versions.androidCore}" }
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }

        object LifeCycle {
            // Architecture
            val liveData by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycle}" }
            val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycle}" }
            val runtime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}" }
        }

        object Navigation {
            val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
            val navigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
        }

        object Views {
            val recylerView by lazy { "androidx.recyclerview:recyclerview:${Versions.recyclerview}" }
            val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
        }

        object UX {
            // Material
            val material by lazy { "com.google.android.material:material:${Versions.material}" }
        }
    }

    object Coroutines {
        val core by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCore}" }
        val android by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroid}" }
    }


    object HttpClient {
        val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
        val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}" }
        val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshiKotlin}" }
        val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.logginInterceptor}" }
    }


    object Glide {
        val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
        val compiler by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    }

    // Palette
//    implementation 'com.androidx.support:palette-v7:28.0.0'

    object Testing {
        val junit by lazy { "junit:junit:${Versions.Testing.junit}" }

        object AndroidTesting {
            val junit by lazy { "androidx.test.ext:junit:${Versions.Testing.AndroidTesting.junit}" }
            val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.Testing.AndroidTesting.espresso}" }
        }
    }
}
