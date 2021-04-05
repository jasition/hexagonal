plugins {
    kotlin("jvm")
    `java-library`
    `java-test-fixtures`
    jacoco
    id ("io.gitlab.arturbosch.detekt") version "1.16.0"
}

val arrowVersion = "0.13.1"
val vavrVersion = "0.10.3"
val junitVersion = "4.12"
val kotestVersion = "4.4.3"
val mockkVersion = "1.10.6"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.arrow-kt:arrow-core:$arrowVersion")
    implementation("io.vavr:vavr:$vavrVersion")

    testImplementation("junit:junit:$junitVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")

    testFixturesImplementation("io.arrow-kt:arrow-core:$arrowVersion")
    testFixturesImplementation("io.vavr:vavr:$vavrVersion")
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
    test {
        jacoco {
            reportsDir = file("$buildDir/reports/coverage")
        }
    }
    check {
        dependsOn(jacocoTestCoverageVerification)
    }
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