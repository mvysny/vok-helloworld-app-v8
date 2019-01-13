package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.server.UserError
import com.vaadin.ui.*

class NewCommentForm : Composite() {
    var commentCreatedListener: ()->Unit = {}
    lateinit var article: Article
    private val commentBinder = beanValidationBinder<Comment>()
    private lateinit var createComment: Button
    private val root = formLayout {
        caption = "Add a comment:"
        textField("Commenter:") {
            bind(commentBinder).bind(Comment::commenter)
        }
        textField("Body:") {
            bind(commentBinder).bind(Comment::body)
        }
        createComment = button("Create") {
            onLeftClick { createComment() }
        }
    }

    private fun createComment() {
        val comment = Comment()
        if (!commentBinder.validate().isOk || !commentBinder.writeBeanIfValid(comment)) {
            createComment.componentError = UserError("There are invalid fields")
        } else {
            createComment.componentError = null
            comment.article_id = article.id
            comment.save()
            commentBinder.readBean(Comment())  // this clears the comment fields
            commentCreatedListener()
        }
    }
}
fun HasComponents.newCommentForm(block: NewCommentForm.()->Unit = {}) = init(NewCommentForm(), block)
