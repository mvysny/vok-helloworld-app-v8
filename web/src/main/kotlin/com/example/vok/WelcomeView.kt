package com.example.vok

import com.github.vok.karibudsl.*
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.server.ThemeResource
import com.vaadin.shared.Version
import com.vaadin.ui.Alignment
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@AutoView("old-welcome")
class WelcomeView: VerticalLayout(), View {
    init {
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
    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
    }
}

val jvmVersion: String get() = System.getProperty("java.version")
