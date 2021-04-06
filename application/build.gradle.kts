import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt") version "1.16.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}

val junitVersion = "4.12"
val kotestVersion = "4.4.3"
val mockkVersion = "1.10.6"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":core"))
    implementation(project(":adapter"))

    testImplementation("junit:junit:$junitVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks {
    detekt {
        toolVersion = "1.16.0"
        config = files("src/test/resources/detekt.yml")
        buildUponDefaultConfig = true

        reports {
            xml {
                enabled = false
            }
            html {
                enabled = true
                destination = file("$buildDir/reports/quality/detekt.html")
            }
            txt {
                enabled = false
            }
        }
    }

    ktlint {
        verbose.set(true)
        outputToConsole.set(false)
        ignoreFailures.set(false)
        reporters {
            reporter(ReporterType.HTML)
        }
    }
}
