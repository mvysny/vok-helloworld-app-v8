package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.navigator.*
import com.vaadin.ui.*
import com.vaadin.ui.renderers.ButtonRenderer
import com.vaadin.ui.themes.ValoTheme
import eu.vaadinonkotlin.vaadin8.sql2o.dataProvider

@AutoView
class ArticlesView: Composite(), View {
    private lateinit var grid: Grid<Article>
    private val root = verticalLayout {
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
            addColumn({ "Destroy" }, ButtonRenderer<Article> { event ->
                confirmDialog("Deleting ${event.item.title}: are you sure?", "Delete Article") {
                    event.item.delete()
                    this@grid.refresh()
                }
            }).id = "destroy"
        }
    }
    override fun enter(event: ViewChangeListener.ViewChangeEvent) {
        grid.refresh()
    }
}
