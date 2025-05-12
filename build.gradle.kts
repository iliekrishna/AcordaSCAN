// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false

}
buildscript {
    dependencies {
        // Adicione esta linha
    classpath("com.google.gms:google-services:4.3.15")
    }
}