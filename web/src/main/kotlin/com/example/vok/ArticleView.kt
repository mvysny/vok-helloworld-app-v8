package com.example.vok

import com.github.vok.framework.sql2o.get
import com.github.vok.karibudsl.*
import com.vaadin.navigator.*
import com.vaadin.ui.FormLayout
import com.vaadin.ui.Label

@AutoView
class ArticleView: FormLayout(), View {
    private val title: Label
    private val text: Label
    init {
        title = label {
            caption = "Title:"
        }
        text = label {
            caption = "Text:"
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
        val articleId = event.parameterList[0]?.toLong() ?: throw RuntimeException("Article ID is missing")
        val article = Article[articleId]
        title.value = article.title
        text.value = article.text
    }

    companion object {
        fun navigateTo(articleId: Long) = navigateToView<ArticleView>(articleId.toString())
    }
}
