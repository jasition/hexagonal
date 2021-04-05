plugins {
    kotlin("jvm")
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

    testImplementation("junit:junit:$junitVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-property:$kotestVersion")
}

task("benchmark", JavaExec::class) {
    group = "JMH_Benchmarking"
    main = "org.openjdk.jmh.Main"
    classpath = sourceSets["test"].compileClasspath + sourceSets["test"].runtimeClasspath
}
