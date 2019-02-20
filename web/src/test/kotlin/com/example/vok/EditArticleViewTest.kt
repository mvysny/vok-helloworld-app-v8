package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.kaributesting.v8._click
import com.github.mvysny.kaributesting.v8._expectOne
import com.github.mvysny.kaributesting.v8._get
import com.vaadin.ui.Button
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import kotlin.test.expect

class EditArticleViewTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        val article = Article(title = "Test Test", text = "Hello World!")
        article.save()
        EditArticleView.navigateTo(article.id!!)
        _expectOne<EditArticleView>()
        expect("Test Test") { _get<TextField> { caption = "Title" } .value }
        expect("Hello World!") { _get<TextArea> { caption = "Text" } .value }
    }

    test("go back") {
        val article = Article(title = "Test Test", text = "Hello World!")
        article.save()
        EditArticleView.navigateTo(article.id!!)
        _get<Button> { caption = "Back" } ._click()
        expectView<ArticlesView>()
    }
})