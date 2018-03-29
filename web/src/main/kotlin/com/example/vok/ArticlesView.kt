package com.example.vok

import com.github.vok.framework.sql2o.vaadin.dataProvider
import com.github.vok.karibudsl.*
import com.vaadin.navigator.*
import com.vaadin.ui.*
import com.vaadin.ui.renderers.ButtonRenderer
import com.vaadin.ui.themes.ValoTheme

@AutoView
class ArticlesView: VerticalLayout(), View {
    private val grid: Grid<Article>
    init {
        setSizeFull()
        label("Listing Articles") {
            styleName = ValoTheme.LABEL_H1
        }
        button("New Article") {
            styleName = ValoTheme.BUTTON_LINK
            onLeftClick { navigateToView<CreateArticleView>() }
        }
        grid = grid(dataProvider = Article.dataProvider) {
            expandRatio = 1f; setSizeFull()
            addColumnFor(Article::id)
            addColumnFor(Article::title)
            addColumnFor(Article::text)
            addColumn({ "Show" }, ButtonRenderer<Article>({ event -> ArticleView.navigateTo(event.item.id!!) }))
            addColumn({ "Edit" }, ButtonRenderer<Article>({ event -> EditArticleView.navigateTo(event.item.id!!) }))
            addColumn({ "Destroy" }, ButtonRenderer<Article>({ event ->
                confirmDialog {
                    event.item.delete()
                    this@grid.refresh()
                }
            }))
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent?) {
        grid.refresh()
    }
}
