package com.example.vok

import com.github.vok.framework.sql2o.get
import com.github.vok.framework.sql2o.vaadin.getAll
import com.github.vok.karibudsl.*
import com.vaadin.navigator.*
import com.vaadin.server.UserError
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme

@AutoView
class ArticleView: FormLayout(), View {
    private lateinit var article: Article
    private val title: Label
    private val text: Label
    private val comments: Label
    private val commentBinder = beanValidationBinder<Comment>()
    private lateinit var createComment: Button
    init {
        title = label {
            caption = "Title:"
        }
        text = label {
            caption = "Text:"
        }
        comments = label { caption = "Comments" }
        formLayout {
            caption = "Add a comment:"
            textField("Commenter:") {
                bind(commentBinder).bind(Comment::commenter)
            }
            textField("Body:") {
                bind(commentBinder).bind(Comment::body)
            }
            createComment = button("Create", { createComment() })
        }
        button("Edit", { EditArticleView.navigateTo(article.id!!) }) {
            styleName = ValoTheme.BUTTON_LINK
        }
        button("Back", { navigateToView<ArticlesView>() }) {
            styleName = ValoTheme.BUTTON_LINK
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
        val articleId = event.parameterList[0]?.toLong() ?: throw RuntimeException("Article ID is missing")
        article = Article[articleId]
        title.value = article.title
        text.value = article.text
    }
    private fun createComment() {
        val comment = Comment()
        if (!commentBinder.validate().isOk || !commentBinder.writeBeanIfValid(comment)) {
            createComment.componentError = UserError("There are invalid fields")
        } else {
            createComment.componentError = null
            comment.article_id = article.id
            comment.save()
            refreshComments()
            commentBinder.readBean(Comment())  // this clears the comment fields
        }
    }
    private fun refreshComments() {
        comments.html(
            // force-update the comments list.
            article.comments.getAll().joinToString("") { comment ->
                "<p><strong>Commenter:</strong>${comment.commenter}</p><p><strong>Comment:</strong>${comment.body}</p>"
            }
        )
    }
    companion object {
        fun navigateTo(articleId: Long) = navigateToView<ArticleView>(articleId.toString())
    }
}
