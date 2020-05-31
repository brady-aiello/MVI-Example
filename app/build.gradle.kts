import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        applicationId = "com.codingwithmitch.mviexample"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    // AndroidX Core
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation ("androidx.constraintlayout:constraintlayout:1.1.3")

    // Retrofit2
    val retrofit2Version = "2.6.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit2Version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit2Version")

    // Lifecycle
    val lifecycleVersion = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-compiler:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")

    // Coroutines
    val coroutinesVersion = "1.2.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // RecyclerView
    val recyclerViewVersion = "1.1.0"
    implementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")

    // Material Design
    val materialVersion = "1.1.0"
    implementation("com.google.android.material:material:$materialVersion")

    // Glide
    val glideVersion = "4.9.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    annotationProcessor("com.github.bumptech.glide:compiler:$glideVersion")

    // Leak Canary
    val leakCanaryVersion = "2.0"
    implementation("com.squareup.leakcanary:leakcanary-android:$leakCanaryVersion")

    // Tests
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
}