package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.karibudsl.v8.navigateToView
import com.github.mvysny.kaributesting.v8.*
import com.github.vokorm.findAll
import com.vaadin.ui.Button
import com.vaadin.ui.Grid
import com.vaadin.ui.Window

class ArticlesViewTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        navigateToView<ArticlesView>()
        _expectOne<ArticlesView>()
        _expectOne<Grid<Article>>()
    }

    test("grid shows all articles") {
        (0..9).forEach { Article(title = "Title $it", text = "Text $it").save() }
        navigateToView<ArticlesView>()
        val grid = _get<Grid<Article>>()
        grid.expectRows(10)
        grid.expectRow(0, Article.findAll()[0].id.toString(), "Title 0", "Text 0", "Show", "Edit", "Destroy")
    }

    test("delete article") {
        Article(title = "Title", text = "Text").save()
        navigateToView<ArticlesView>()
        val grid = _get<Grid<Article>>()
        grid.expectRows(1)
        grid._clickRenderer(0, "destroy")
        // a confirm dialog should be displayed
        _expectOne<Window> { caption = "Delete Article" }
        _get<Button> { caption = "Yes" } ._click()
        expectList() { Article.findAll() }
    }
})
