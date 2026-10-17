plugins {
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "com.onionsoap"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

javafx {
    version = "25"
    modules = listOf(
        "javafx.base",
        "javafx.controls",
        "javafx.graphics"
    )
}

application {
    mainClass = "com.onionsoap.econosim.Main"
}