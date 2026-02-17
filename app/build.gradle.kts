plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.zappyware.moviebrowser"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.zappyware.moviebrowser"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(type = "String", name = "API_KEY", value = "\"ca510ea19628472479576813e59b1b7d\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Core
    implementation(libs.kotlin)
    implementation(libs.androidx.core.ktx)

    // Compose
    implementation(libs.androidx.compose.activity)
    implementation(libs.compose.lifecycle.viewmodel)
    implementation(libs.compose.material)
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.compose)

    // Data
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.datastore)
    implementation(libs.room)
    implementation(libs.room.ktx)
    implementation(libs.okhttp.logger)

    // Coroutines
    implementation(libs.kotlin.coroutines)

    // DI
    implementation(libs.dagger)

    // Utils
    implementation(libs.gson)

    // Kapt
    ksp(libs.dagger.compiler)
    ksp(libs.room.compiler)

    implementation(projects.core.data)
    implementation(projects.core.commonUi)
    implementation(projects.core.backend)
    implementation(projects.core.repository)

    implementation(projects.page.landingPage)
    implementation(projects.page.detailPage)
}
