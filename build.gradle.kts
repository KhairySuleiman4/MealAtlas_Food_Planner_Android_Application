// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies{
        classpath(libs.navigation.safe.args.gradle.plugin)
        classpath(libs.gradle)
        classpath(libs.google.services)
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}