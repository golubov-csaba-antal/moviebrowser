plugins {
    id("com.android.library")
    id("com.google.devtools.ksp")
    alias(libs.plugins.kotlin.compose)
}

android {
    // Aligned with the source project's namespace
    namespace = "com.zappyware.moviebrowser.core.uikit"
    compileSdk = 36

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.activity)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Core
    implementation(libs.kotlin)
    implementation(libs.android.appcompat)
    implementation(libs.androidx.core.ktx)

    // Compose
    implementation(libs.androidx.compose.activity)
    implementation(libs.compose.lifecycle.viewmodel)
    implementation(libs.compose.material)
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // Navigation
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.adaptive.navigation3)
    implementation(libs.kotlinx.serialization.core)

    // Data
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.datastore)
    implementation(libs.room)
    implementation(libs.room.ktx)
    implementation(libs.okhttp.logger)

    // Coroutines
    implementation(libs.kotlin.coroutines)

    // DI - Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)

    // Utils
    implementation(libs.gson)

    // KSP
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)

    // Tooling
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // This is the standard way to include a local AAR
    api(files("libs/uikit-debug.aar"))
}
