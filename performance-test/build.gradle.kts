import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    kotlin("jvm")
    id("io.gitlab.arturbosch.detekt") version "1.16.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}

val commonIoVersion = "2.4"
val junitVersion = "4.12"
val kotestVersion = "4.4.3"
val jmhVersion = "1.29"

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":core"))
    implementation("commons-io:commons-io:$commonIoVersion")
    implementation("org.openjdk.jmh:jmh-core:$jmhVersion")
    annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")

    testImplementation(testFixtures(project(":core")))
    testImplementation("junit:junit:$junitVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
    testAnnotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")
}

task("benchmark", JavaExec::class) {
    group = "JMH_Benchmarking"
    main = "org.openjdk.jmh.Main"
    classpath = sourceSets["test"].compileClasspath + sourceSets["test"].runtimeClasspath

    val forceGc = true
    val reportFormat = "json"
    val reportBaseDir = "$buildDir/reports"
    val reportFileLocation = "$reportBaseDir/jmh-report.json"
    val outputFileLocation = "$reportBaseDir/jmh.log"

    args = listOf(
        "-gc", "$forceGc",
        "-o", outputFileLocation,
        "-rf", reportFormat,
        "-rff", reportFileLocation
    )
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
