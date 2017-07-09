package com.example.vok

import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.karibudsl.*
import com.vaadin.navigator.*
import com.vaadin.ui.*
import com.vaadin.ui.renderers.ButtonRenderer
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
        button("New Article", { navigateToView<CreateArticleView>() }) {
            styleName = ValoTheme.BUTTON_LINK
        }
        grid = grid(Article::class, null, dataSource) {
            expandRatio = 1f; setSizeFull()
            showColumns(Article::id, Article::title, Article::text)
            addColumn({ "Show" }, ButtonRenderer<Article>({ event -> ArticleView.navigateTo(event.item.id!!) }))
            addColumn({ "Edit" }, ButtonRenderer<Article>({ event -> EditArticleView.navigateTo(event.item.id!!) }))
            addColumn({ "Destroy" }, ButtonRenderer<Article>({ event ->
                confirmDialog {
                    event.item.delete()
                    this@grid.dataProvider.refreshAll()
                }
            }))
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
        grid.dataProvider.refreshAll()
    }
}
