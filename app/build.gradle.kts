import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

// Здесь читается API-ключ из local.properties
val movieApi: String = gradleLocalProperties(rootDir).getProperty("THE_MOVIE_API")

android {
    namespace = "com.rxjavasamples"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rxjavasamples"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "API_URL", movieApi)
        }
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
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // RxJava
    implementation ("io.reactivex.rxjava3:rxjava:3.0.0")
    // RxAndroid
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")
    // Для использования RxJava c Retrofit
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // GSON
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // Logging-interceptor
    implementation ("com.squareup.okhttp3:logging-interceptor:4.1.1")

    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.picasso:picasso:2.8")
}