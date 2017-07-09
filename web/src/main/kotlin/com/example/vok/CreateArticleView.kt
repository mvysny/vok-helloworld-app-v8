package com.example.vok

import com.github.vok.karibudsl.*
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@AutoView
class CreateArticleView: VerticalLayout(), View {
    private val binder = beanValidationBinder<Article>()
    init {
        label("New Article") {
            styleName = ValoTheme.LABEL_H1
        }
        textField("Title") {
            bind(binder).bind(Article::title)
        }
        textArea("Text") {
            bind(binder).bind(Article::text)
        }
        button("Save Article", {
        })
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
    }
}
