package com.example.vok

import com.github.vok.karibudsl.*
import com.github.vokorm.getById
import com.vaadin.navigator.*
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme

@AutoView
class EditArticleView : VerticalLayout(), View {
    private val editor: ArticleEditor
    init {
        label("Edit Article") {
            styleName = ValoTheme.LABEL_H1
        }
        editor = articleEditor()
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
        val articleId = event.parameterList[0]!!.toLong()
        editor.article = Article.getById(articleId)
    }

    companion object {
        fun navigateTo(articleId: Long) = navigateToView<EditArticleView>(articleId.toString())
    }
}
