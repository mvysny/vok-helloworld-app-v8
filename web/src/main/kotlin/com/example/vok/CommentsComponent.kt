package com.example.vok

import com.github.vok.karibudsl.*
import com.github.vokorm.getById
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme

class CommentsComponent : VerticalLayout() {
    var articleId: Long = 0L
        set(value) { field = value; refresh() }
    init {
        caption = "Comments"; isMargin = false
    }

    fun refresh() {
        removeAllComponents()
        Article.getById(articleId).comments.getAll().forEach { comment ->
            label {
                html("<p><strong>Commenter:</strong>${comment.commenter}</p><p><strong>Comment:</strong>${comment.body}</p>")
            }
            button("Delete comment") {
                styleName = ValoTheme.BUTTON_LINK
                onLeftClick { comment.delete(); refresh() }
            }
        }
    }
}
// the extension function which will allow us to use CommentsComponent inside a DSL
fun HasComponents.commentsComponent(block: CommentsComponent.()->Unit = {}) = init(CommentsComponent(), block)
