import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    `multiplatform-library`
}

apply(plugin = "kotlinx-atomicfu")

kotlin {
    jvm {
        withJava()
    }

    js {
        browser {
            useCommonJs()
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDirs("src/commonMain/generated")

            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation(Libs.googleTruth)
            }
        }

        val darwinMain by getting {
            dependencies {
                implementation(Libs.statelyIsolate)
                implementation(Libs.AtomicFU.native)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation("com.github.samgarasx:kotlin-moment:2.24.0-pre.2-kotlin-1.3.72")
                implementation(npm("moment", "2.24.0"))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

tasks.withType<DokkaTask>().configureEach {
    multiplatform {
        create("global") {
            perPackageOption {
                prefix = "io.islandtime.internal"
                suppress = true
            }

            perPackageOption {
                prefix = "io.islandtime.measures.internal"
                suppress = true
            }

            perPackageOption {
                prefix = "io.islandtime.parser.internal"
                suppress = true
            }

            perPackageOption {
                prefix = "io.islandtime.ranges.internal"
                suppress = true
            }

            perPackageOption {
                includes = listOf("packages.md")
            }
        }
    }
}