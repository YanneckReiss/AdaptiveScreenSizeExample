// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra["lifecycleVersion"] = "2.6.2"
    extra["composeDestinationsVersion"] = "1.9.53"
}
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}