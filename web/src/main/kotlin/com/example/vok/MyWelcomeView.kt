package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.navigator.View
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@AutoView("")
class MyWelcomeView: VerticalLayout(), View {
    init {
        verticalLayout {
            label("Hello, Vaadin-on-Kotlin!") {
                styleName = ValoTheme.LABEL_H1
            }
            button("My Blog") {
                styleName = ValoTheme.BUTTON_LINK
                onLeftClick { navigateToView<ArticlesView>() }
            }
        }
    }
}
