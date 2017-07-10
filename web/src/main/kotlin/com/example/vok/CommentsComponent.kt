package com.example.vok

import com.github.vok.framework.sql2o.get
import com.github.vok.framework.sql2o.vaadin.getAll
import com.github.vok.karibudsl.*
import com.vaadin.ui.*

class CommentsComponent : VerticalLayout() {
    var articleId: Long = 0L
        set(value) { field = value; refresh() }
    init {
        caption = "Comments"; isMargin = false
    }

    fun refresh() {
        removeAllComponents()
        Article[articleId].comments.getAll().forEach { comment ->
            label {
                html("<p><strong>Commenter:</strong>${comment.commenter}</p><p><strong>Comment:</strong>${comment.body}</p>")
            }
        }
    }
}
// the extension function which will allow us to use CommentsComponent inside a DSL
fun HasComponents.commentsComponent(block: CommentsComponent.()->Unit = {}) = init(CommentsComponent(), block)
