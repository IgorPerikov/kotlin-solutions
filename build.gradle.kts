import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
}

group = "com.github.igorperikov"
version = "0.0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

val codecsVersion by extra { "1.11" }
val guavaVersion by extra { "27.0-jre" }
val junit5Version by extra { "5.2.0" }
val hamkrestVersion by extra { "1.6.0.0" }

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("com.google.guava", "guava", guavaVersion)
    implementation("commons-codec", "commons-codec", codecsVersion)

    testImplementation("com.natpryce", "hamkrest", hamkrestVersion)

    testImplementation("org.junit.jupiter", "junit-jupiter-api", junit5Version)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junit5Version)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junit5Version)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("junit-jupiter")
    }
}
