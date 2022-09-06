import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.0"
}

group = "com.cedware"
version = "1.0-SNAPSHOT"
val ktor_version = "2.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")
    implementation("org.litote.kmongo:kmongo:4.7.0")
    implementation("org.litote.kmongo:kmongo-async:4.7.0")
    implementation("org.litote.kmongo:kmongo-coroutine:4.7.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}