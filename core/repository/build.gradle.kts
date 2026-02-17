plugins {
    id("com.android.library")
}

android {
    namespace = "com.zappyware.moviebrowser.repository"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.kotlin.coroutines)
    implementation(libs.dagger)

    implementation(projects.core.data)
    implementation(projects.core.commonUi)
    implementation(projects.core.backend)
}
