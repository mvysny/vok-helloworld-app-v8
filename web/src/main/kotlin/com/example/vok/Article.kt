package com.example.vok

import com.github.vokorm.*
import eu.vaadinonkotlin.vaadin8.sql2o.*
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class Article(
        override var id: Long? = null,

        @field:NotNull
        @field:Length(min = 5)
        var title: String? = null,

        var text: String? = null
) : Entity<Long> {
    companion object : Dao<Article>

    val comments: VokDataProvider<Comment> get() = Comment.dataProvider.withFilter { Comment::article_id eq id }

    override fun delete() {
        Comment.deleteBy { Comment::article_id eq id }
        super.delete()
    }
}
