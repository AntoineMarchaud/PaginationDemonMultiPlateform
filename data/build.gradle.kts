plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.serializable)
    alias(libs.plugins.room.plugin)
    alias(libs.plugins.com.google.devtools.ksp)
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
            implementation(libs.roomPaging)
            implementation(libs.roomktx)
            implementation(libs.roomRuntime.android)

            implementation(libs.koin.android)
            implementation(libs.koin.compose)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.stately.common)
            implementation(libs.stately.isolate)
            implementation(libs.stately.iso.collection)
        }

        val commonMain by getting {
            dependencies {
                implementation(project(":domain"))
                implementation(libs.koin.core)

                implementation(libs.ktor.core)
                implementation(libs.ktor.client.serialization)
                implementation(libs.ktor.client.content)
                implementation(libs.coil)
                implementation(libs.coil.network.ktor)

                implementation(libs.paging.common)
                implementation(libs.paging.compose.common)

                implementation(libs.kotlinx.datetime)

                // bdd
                implementation(libs.sqliteBundled)
                implementation(libs.roomRuntime)
            }
        }
    }
}

dependencies {
    add("kspAndroid", libs.roomCompiler)
    add("kspIosSimulatorArm64", libs.roomCompiler)
    add("kspIosArm64", libs.roomCompiler)
    add("kspIosX64", libs.roomCompiler)
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

room {
    schemaDirectory("$projectDir/schemas")
}

