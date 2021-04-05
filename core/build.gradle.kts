plugins {
    kotlin("jvm")
    id ("java-test-fixtures")
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

}