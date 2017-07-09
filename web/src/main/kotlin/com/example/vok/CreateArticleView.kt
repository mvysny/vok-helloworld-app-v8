package com.example.vok

import com.github.vok.karibudsl.*
import com.vaadin.navigator.*
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
            val article = Article()
            if (binder.writeBeanIfValid(article)) {
                article.save()
                ArticleView.navigateTo(article.id!!)
            }
        })
        button("Back", { navigateToView<ArticlesView>() }) {
            styleName = ValoTheme.BUTTON_LINK
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
    }
}
