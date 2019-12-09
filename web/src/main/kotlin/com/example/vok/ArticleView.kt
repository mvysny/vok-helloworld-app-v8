package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.navigator.*
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme

@AutoView
class ArticleView: Composite(), View {
    private lateinit var article: Article
    private lateinit var title: Label
    private lateinit var text: Label
    private lateinit var comments: CommentsComponent
    private lateinit var newComment: NewCommentForm
    private val root = verticalLayout {
        formLayout {
            title = label {
                caption = "Title:"
            }
            text = label {
                caption = "Text:"
            }
        }
        comments = commentsComponent()
        newComment = newCommentForm {
            commentCreatedListener = { comments.refresh() }
        }
        button("Edit") {
            styleName = ValoTheme.BUTTON_LINK
            onLeftClick { EditArticleView.navigateTo(article.id!!) }
        }
        button("Back") {
            styleName = ValoTheme.BUTTON_LINK
            onLeftClick { navigateToView<ArticlesView>() }
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
        val articleId = event.parameterList[0]?.toLong() ?: throw RuntimeException("Article ID is missing")
        article = Article.getById(articleId)
        title.value = article.title
        text.value = article.text
        comments.articleId = article.id!!
        newComment.article = article
    }

    companion object {
        fun navigateTo(articleId: Long) = navigateToView<ArticleView>(articleId.toString())
    }
}
