import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.30"
    application
}

group = "me.jasminequah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    /* kotlin test */
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.assertj:assertj-core:3.20.2")

    /* kotlin utils */
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.4")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
