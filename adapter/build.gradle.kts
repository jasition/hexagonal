import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    kotlin("jvm")
    jacoco
    id("io.gitlab.arturbosch.detekt") version "1.16.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}

val junitVersion = "4.12"
val kotestVersion = "4.4.3"
val mockkVersion = "1.10.6"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":core"))

    testImplementation("junit:junit:$junitVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks {
    jacocoTestReport {
        reports {
            xml.isEnabled = false
            csv.isEnabled = false
            html.destination = file("$buildDir/reports/coverage")
        }
    }
    jacocoTestCoverageVerification {
        violationRules {
            rule {
                limit {
                    minimum = BigDecimal.ONE
                }
            }
        }
        dependsOn(jacocoTestReport)
    }

    jacoco {
        toolVersion = "0.8.6"
        reportsDir = file("$buildDir/reports/coverage")
    }

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

    test {
        jacoco {
            reportsDir = file("$buildDir/reports/coverage")
        }
    }
    check {
        dependsOn(jacocoTestCoverageVerification)
    }
}
