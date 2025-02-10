// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies{
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.6")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
}