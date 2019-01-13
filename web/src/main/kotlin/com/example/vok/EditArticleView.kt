package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.github.vokorm.getById
import com.vaadin.navigator.*
import com.vaadin.ui.Composite
import com.vaadin.ui.themes.ValoTheme

@AutoView
class EditArticleView : Composite(), View {
    private lateinit var editor: ArticleEditor
    private val root = verticalLayout {
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
