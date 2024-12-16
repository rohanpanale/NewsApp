plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    configurations.all {
        resolutionStrategy {
            force("androidx.core:core:1.13.0")  // Force a specific version
            force("androidx.appcompat:appcompat:1.6.0")  // Example
        }
    }


    // AndroidX Libraries
    implementation(libs.appcompat) // AndroidX AppCompat
    implementation(libs.material) // AndroidX Material Components
    implementation(libs.retrofit) // Retrofit for networking
    implementation(libs.converter.gson) // Gson Converter for Retrofit
    implementation(libs.logging.interceptor) // Logging Interceptor for Retrofit
    implementation(libs.picasso) // Image loading library (Picasso)
    implementation(libs.core) // AndroidX Core library
    implementation(libs.core.v1130) // Specific version of AndroidX Core
    implementation(libs.recyclerview) // RecyclerView for lists and grids

    // Test Libraries
    testImplementation(libs.junit) // JUnit for unit tests
    androidTestImplementation(libs.ext.junit) // JUnit extensions for UI tests
    androidTestImplementation(libs.espresso.core) // Espresso for UI testing

        implementation(libs.picasso)



}