package com.example.vok

import com.github.vok.karibudsl.*
import com.vaadin.server.UserError
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme

class ArticleEditor : VerticalLayout() {
    private val binder = beanValidationBinder<Article>()
    var article: Article? = null
        set(value) {
            field = value
            if (value != null) binder.readBean(value)
        }

    init {
        isMargin = false
        textField("Title") {
            bind(binder).bind(Article::title)
        }
        textArea("Text") {
            bind(binder).bind(Article::text)
        }
        button("Save Article") {
            onLeftClick { event ->
                val article = article!!
                if (binder.validate().isOk && binder.writeBeanIfValid(article)) {
                    article.save()
                    ArticleView.navigateTo(article.id!!)
                } else {
                    event.button.componentError = UserError("There are invalid fields")
                }
            }
        }
        button("Back") {
            styleName = ValoTheme.BUTTON_LINK
            onLeftClick { navigateToView<ArticlesView>() }
        }
    }
}

fun HasComponents.articleEditor(block: ArticleEditor.()->Unit = {}) = init(ArticleEditor(), block)
