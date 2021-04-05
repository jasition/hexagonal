plugins {
    kotlin("jvm")
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

