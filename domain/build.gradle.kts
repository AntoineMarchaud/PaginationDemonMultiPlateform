plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.org.jetbrains.kotlin.serializable)
}


kotlin {
    jvmToolchain(17)
    jvm()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "domain"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.paging.common)
                // Test
                // testImplementation(libs.bundles.test)
            }
        }
    }
}
