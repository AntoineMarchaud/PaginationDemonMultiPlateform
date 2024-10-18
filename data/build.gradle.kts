plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.serializable)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "data"
            isStatic = true
        }
    }


    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android.driver)

            implementation(libs.koin.android)
            implementation(libs.koin.compose)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.stately.common)
            implementation(libs.stately.isolate)
            implementation(libs.stately.iso.collection)
            implementation(libs.sqldelight.native.driver)
        }

        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(libs.koin.core)

                implementation(libs.ktor.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.content)
                implementation(libs.sqldelight.paging)
                implementation(libs.sqldelight.runtime)
                implementation(libs.coil)
                implementation(libs.coil.network.ktor)

                implementation(libs.paging.common)
                implementation(libs.paging.compose.common)

                implementation(libs.kotlinx.datetime)
            }
        }
    }
}

android {
    namespace = "com.amarchaud.data"
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


sqldelight {
    databases {
        register("PaginationDemoDatabase") { // The name of the database
            packageName.set("com.amarchaud.database") // Package name used for the database class.
        }
    }
}
