plugins {
    id("java")
}

group = "one.group"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("org.jetbrains:annotations:24.0.0")
    // Source: https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.5.29")
    // Source: https://mvnrepository.com/artifact/ch.qos.logback/logback-core
    implementation("org.slf4j:slf4j-api:2.0.13")
}

tasks.test {
    useJUnitPlatform()
}