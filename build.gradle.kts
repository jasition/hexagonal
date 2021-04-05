plugins {
    base
    kotlin("jvm") version "1.4.32"
}

val targetJvmVersion = "11"
val commonIoVersion = "2.4"
val jmhVersion = "0.9"

allprojects {
    group = "jasition"
    version = "1.0"

    repositories {
        jcenter()
    }

    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions {
                jvmTarget = targetJvmVersion
            }
        }
        withType<Test> {
            useJUnitPlatform()
        }
    }
}

dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }

    testImplementation("commons-io:commons-io:$commonIoVersion")
    testImplementation("org.openjdk.jmh:jmh-core:$jmhVersion")
    testImplementation("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")
}
