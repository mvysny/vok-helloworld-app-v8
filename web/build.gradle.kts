plugins {
    war
    id("org.gretty")
    id("com.devsoap.plugin.vaadin")
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
}

vaadin {
    version = properties["vaadin8_version"].toString()
}

dependencies {
    compile("eu.vaadinonkotlin:vok-framework-vokdb:${properties["vok_version"]}")
    compile("com.zaxxer:HikariCP:3.4.1")

    // logging
    // currently we are logging through the SLF4J API to LogBack. See logback.xml file for the logger configuration
    compile("ch.qos.logback:logback-classic:1.2.3")
    compile("org.slf4j:slf4j-api:1.7.28")
    // this will configure Vaadin to log to SLF4J
    compile("org.slf4j:jul-to-slf4j:1.7.28")

    // workaround until https://youtrack.jetbrains.com/issue/IDEA-178071 is fixed
    compile("com.vaadin:vaadin-themes:${vaadin.version}")
    compile("com.vaadin:vaadin-push:${vaadin.version}")
    compile("com.vaadin:vaadin-client-compiled:${vaadin.version}")
    providedCompile("javax.servlet:javax.servlet-api:3.1.0")

    // db
    compile("org.flywaydb:flyway-core:6.1.0")
    compile("com.h2database:h2:1.4.200")

    // REST
    compile("eu.vaadinonkotlin:vok-rest:${properties["vok_version"]}")

    // Kotlin
    compile(kotlin("stdlib-jdk8"))
}
