plugins {
    alias(libs.plugins.android.application)
    id("androidx.navigation.safeargs")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.foodplanner"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodplanner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.legacy.support.v4)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation (libs.glide)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.rxjava3.retrofit.adapter)
    implementation(libs.rxandroid)
    implementation(libs.rxjava)
    implementation(libs.room.rxjava3)
    implementation(libs.lottie)
    implementation(libs.play.services.auth)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
}