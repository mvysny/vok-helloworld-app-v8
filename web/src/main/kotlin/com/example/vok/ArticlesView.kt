package com.example.vok

import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.karibudsl.*
import com.vaadin.navigator.*
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme

@AutoView
class ArticlesView: VerticalLayout(), View {
    private val dataSource = Article.dataProvider
    private val grid: Grid<Article>
    init {
        setSizeFull()
        label("Listing Articles") {
            styleName = ValoTheme.LABEL_H1
        }
        grid = grid(Article::class, null, dataSource) {
            expandRatio = 1f; setSizeFull()
            showColumns(Article::id, Article::title, Article::text)
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
        grid.dataProvider.refreshAll()
    }
}
