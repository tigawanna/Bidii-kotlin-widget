plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose) // Corrected alias for Compose plugin
    alias(libs.plugins.kotlin.serialization) // Corrected alias for Kotlin Serialization plugin
}

android {
    namespace = "com.tigawanna.bidii"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tigawanna.bidii"
        minSdk = 24
        targetSdk = 35
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // For AppWidgets support
    implementation("androidx.glance:glance-appwidget:1.1.1")

    // For interop APIs with Material 3
    implementation("androidx.glance:glance-material3:1.1.1")

    // For interop APIs with Material 2
    implementation("androidx.glance:glance-material:1.1.1")

    // Ktor for networking
    implementation("io.ktor:ktor-client-android:2.3.8")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.8")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.8")
    implementation("io.ktor:ktor-client-logging:2.3.8")

    // Kotlinx Serialization for JSON parsing
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // DataStore for preferences - ensure this is the latest stable version
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Ktor (ensure versions are consistent and up-to-date)
    implementation("io.ktor:ktor-client-core:2.3.8") // ktor-client-android includes this
    implementation("io.ktor:ktor-client-serialization:2.3.8") // ktor-client-content-negotiation is preferred

    // Logback for Ktor logging (if needed, ensure it's compatible)
    implementation("ch.qos.logback:logback-classic:1.4.14") // Updated to a more recent version
}
