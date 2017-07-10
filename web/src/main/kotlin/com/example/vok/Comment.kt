package com.example.vok

import com.fasterxml.jackson.annotation.JsonIgnore
import com.github.vok.framework.sql2o.*
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

data class Comment(
        override var id: Long? = null,

        var article_id: Long? = null,

        @field:NotNull
        @field:Length(min = 3)
        var commenter: String? = null,

        @field:NotNull
        @field:Length(min = 3)
        var body: String? = null
) : Entity<Long> {
    companion object : Dao<Comment>

    @JsonIgnore
    var article: Article? = if (article_id == null) null else Article.findById(article_id!!)
}
