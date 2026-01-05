plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "com.compilercastle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Placeholder for AST library
    // implementation("com.github.kastree:kastree-ast-psi:0.4.0") 
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.compilercastle.MainKt")
}
