import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.10"
    // need to use Gretty here because of https://github.com/johndevs/gradle-vaadin-plugin/issues/317
    id("org.gretty") version "2.2.0"
    id("com.devsoap.plugin.vaadin") version "1.4.1"
}

defaultTasks("clean", "build")


allprojects {
    group = "com.example.vok"
    version = "1.0-SNAPSHOT"
}

subprojects {

    repositories {
        jcenter()
    }

    apply { plugin("kotlin") }
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            // to see the exceptions of failed tests in Travis-CI console.
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
}
