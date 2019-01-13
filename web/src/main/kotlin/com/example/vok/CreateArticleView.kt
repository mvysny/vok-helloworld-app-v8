package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.navigator.*
import com.vaadin.ui.Composite
import com.vaadin.ui.themes.ValoTheme

@AutoView
class CreateArticleView: Composite(), View {
    private lateinit var editor: ArticleEditor

    private val root = verticalLayout {
        label("New Article") {
            styleName = ValoTheme.LABEL_H1
        }
        editor = articleEditor {
            article = Article()
        }
    }
}
