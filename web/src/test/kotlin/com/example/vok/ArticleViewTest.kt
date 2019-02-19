package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.kaributesting.v8._click
import com.github.mvysny.kaributesting.v8._get
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import kotlin.test.expect

class ArticleViewTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        val article = Article(title = "Test Test", text = "Hello World!")
        article.save()
        ArticleView.navigateTo(article.id!!)
        expect("Test Test") { _get<Label> { caption = "Title:" } .value }
        expect("Hello World!") { _get<Label> { caption = "Text:" } .value }
    }

    test("go back") {
        val article = Article(title = "Test Test", text = "Hello World!")
        article.save()
        ArticleView.navigateTo(article.id!!)
        _get<Button> { caption = "Back" } ._click()
        expectView<ArticlesView>()
    }
})
