package com.example.vok

import com.github.vok.karibudsl.*
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@AutoView("")
class MyWelcomeView: VerticalLayout(), View {
    init {
        label("Hello, Vaadin-on-Kotlin!") {
            styleName = ValoTheme.LABEL_H1
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
    }
}
