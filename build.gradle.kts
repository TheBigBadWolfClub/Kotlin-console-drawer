import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "drawing.console"
version = "0.0.1"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation(kotlin("test-junit5"))
    testImplementation("io.mockk:mockk:1.10.2")
    testImplementation("org.assertj:assertj-core:3.18.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test> {
    useJUnitPlatform()
}
val testProvider: TaskProvider<Test> = tasks.test
application {
    mainClassName = "MainKt"
}

repositories {
    mavenCentral()
    mavenLocal()
}
