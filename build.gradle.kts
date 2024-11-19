plugins {
    application
}

application {
    mainClass = "io.github.anatoly_gz.uno_soft_java_coding_test.Main"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

// jar должен запускаться следующим образом: `java -jar {название проекта}.jar тестовый-файл.txt`
tasks {
    named<Jar>("jar") {
        destinationDirectory.set(rootDir)
        manifest {
            attributes["Main-Class"] = application.mainClass
        }
    }

    named<Delete>("clean") {
        delete += fileTree(rootDir).matching { include("*.jar") }
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

repositories {
    mavenCentral()
}