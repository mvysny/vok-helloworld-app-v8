package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.github.vokorm.getById
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme

class CommentsComponent : Composite() {
    var articleId: Long = 0L
        set(value) { field = value; refresh() }
    private val root = verticalLayout {
        caption = "Comments"; isMargin = false
    }

    fun refresh() {
        root.removeAllComponents()
        Article.getById(articleId).comments.getAll().forEach { comment ->
            root.apply {
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
}
// the extension function which will allow us to use CommentsComponent inside a DSL
fun HasComponents.commentsComponent(block: CommentsComponent.()->Unit = {}) = init(CommentsComponent(), block)
