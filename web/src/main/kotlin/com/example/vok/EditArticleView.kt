package com.example.vok

import com.github.vok.framework.sql2o.get
import com.github.vok.karibudsl.*
import com.vaadin.navigator.*
import com.vaadin.server.UserError
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@AutoView
class EditArticleView: VerticalLayout(), View {
    private val binder = beanValidationBinder<Article>()
    private var article: Article? = null
    init {
        label("Edit Article") {
            styleName = ValoTheme.LABEL_H1
        }
        textField("Title") {
            bind(binder).bind(Article::title)
        }
        textArea("Text") {
            bind(binder).bind(Article::text)
        }
        button("Save Article", { event ->
            val article = Article()
            if (binder.validate().isOk && binder.writeBeanIfValid(article)) {
                article.save()
                ArticleView.navigateTo(article.id!!)
            } else {
                event.button.componentError = UserError("There are invalid fields")
            }
        })
        button("Back", { navigateToView<ArticlesView>() }) {
            styleName = ValoTheme.BUTTON_LINK
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
        val articleId = event.parameterList[0]!!.toLong()
        edit(Article[articleId])
    }

    private fun edit(article: Article) {
        this.article = article
        binder.readBean(article)
    }

    companion object {
        fun navigateTo(articleId: Long) = navigateToView<EditArticleView>(articleId.toString())
    }
}
