package com.example.vok

import com.github.vok.framework.sql2o.Dao
import com.github.vok.framework.sql2o.Entity

data class Article(
        override var id: Long? = null,

        var title: String? = null,

        var text: String? = null
) : Entity<Long> {
    companion object : Dao<Article>
}
