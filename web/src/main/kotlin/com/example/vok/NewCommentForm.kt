package com.example.vok

import com.github.vok.karibudsl.*
import com.vaadin.server.UserError
import com.vaadin.ui.*

class NewCommentForm : FormLayout() {
    var commentCreatedListener: ()->Unit = {}
    lateinit var article: Article
    private val commentBinder = beanValidationBinder<Comment>()
    private val createComment: Button
    init {
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
