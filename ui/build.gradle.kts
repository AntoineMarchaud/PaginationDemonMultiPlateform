plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.org.jetbrains.kotlin.serializable)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "ui"
            isStatic = true
        }
    }


    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.koin.compose)

            // common google
            implementation(compose.preview)
            implementation(libs.activity.compose)
           // implementation(libs.paging.common.android)
           // implementation(libs.paging.compose)
            implementation(libs.lifecycle.viewmodel.ktx)
            //implementation(libs.lifecycle.viewmodel.savedstate.android) // alpha

        }

        iosMain.dependencies {
            implementation(libs.stately.common)
            implementation(libs.stately.isolate)
            implementation(libs.stately.iso.collection)
        }

        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.koin.compose.viewmodel.navigation)

                implementation(libs.coil)
                implementation(libs.coil.network.ktor)

                // common google
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.paging.common)
                implementation(libs.paging.compose.common)
                implementation(libs.lifecycle.common)
                implementation(libs.lifecycle.runtime)
                implementation(libs.lifecycle.viewmodel)
                implementation(libs.lifecycle.viewmodel.savedstate)

                implementation(libs.kotlinx.datetime)
            }
        }
    }
}

android {
    namespace = "com.amarchaud.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    kotlin.sourceSets.configureEach {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
}

kotlin {
    jvmToolchain(21)
}
