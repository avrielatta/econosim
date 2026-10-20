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

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.1.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClass = "com.onionsoap.econosim.Main"
}