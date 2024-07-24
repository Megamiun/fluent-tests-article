plugins {
    kotlin("jvm")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.hamcrest:hamcrest:2.2")
    testImplementation("org.hobsoft.hamcrest:hamcrest-compose:0.5.0")

    testImplementation("org.assertj:assertj-core:3.25.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    test {
        useJUnitPlatform()
    }
}