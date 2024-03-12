// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath ("com.android.tools.build:gradle:3.4.0")
        classpath ("com.google.gms:google-services:4.3.15")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.4")
    }
}

plugins {
    id ("com.android.application") version "7.4.1" apply false
    id ("com.android.library") version "7.4.1" apply false
    id ("org.jetbrains.kotlin.android") version ("1.8.0") apply false
    id ("com.google.dagger.hilt.android") version ("2.44") apply false
    id ("io.realm.kotlin") version "1.7.0" apply false
    id ("org.jetbrains.kotlin.kapt") version "1.6.20" apply false

}