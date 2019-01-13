package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.navigator.View
import com.vaadin.server.ThemeResource
import com.vaadin.shared.Version
import com.vaadin.ui.Alignment
import com.vaadin.ui.Composite
import com.vaadin.ui.themes.ValoTheme

@AutoView("old-welcome")
class WelcomeView: Composite(), View {
    private val root = verticalLayout {
        setSizeFull()
        isMargin = false
        verticalLayout {
            alignment = Alignment.MIDDLE_CENTER
            isMargin = false; isSpacing = true; defaultComponentAlignment = Alignment.MIDDLE_CENTER
            label("Yay! You're on Vaadin-on-Kotlin!") {
                styleName = ValoTheme.LABEL_H1
            }
            image(resource = ThemeResource("images/chucknorris.jpg"))
            label { html("<strong>Vaadin version: </strong> ${Version.getFullVersion()}") }
            label { html("<strong>Kotlin version: </strong> ${KotlinVersion.CURRENT}") }
            label { html("<strong>JVM version: </strong> $jvmVersion") }
        }
    }
}

val jvmVersion: String get() = System.getProperty("java.version")
