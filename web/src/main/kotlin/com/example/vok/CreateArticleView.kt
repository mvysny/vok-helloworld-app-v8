package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.navigator.*
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@AutoView
class CreateArticleView: VerticalLayout(), View {
    private val editor: ArticleEditor
    init {
        label("New Article") {
            styleName = ValoTheme.LABEL_H1
        }
        editor = articleEditor {
            article = Article()
        }
    }
}
