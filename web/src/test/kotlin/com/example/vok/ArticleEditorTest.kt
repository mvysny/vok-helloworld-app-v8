package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.karibudsl.v8.message
import com.github.mvysny.kaributesting.v8._click
import com.github.mvysny.kaributesting.v8._get
import com.github.mvysny.kaributesting.v8._value
import com.github.mvysny.kaributesting.v8.expectView
import com.github.vokorm.findAll
import com.vaadin.ui.Button
import com.vaadin.ui.TextArea
import com.vaadin.ui.TextField
import kotlin.test.expect

class ArticleEditorTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        ArticleEditor()
    }
    test("populate fields") {
        val a = Article(title = "Foo", text = "Bar")
        val editor = ArticleEditor()
        editor.article = a
        expect("Foo") { editor._get<TextField> { caption = "Title" }._value }
        expect("Bar") { editor._get<TextArea>()._value }
    }

    test("save succeeds if article is valid") {
        val editor = ArticleEditor()
        editor.article = Article()
        editor._get<TextField> { caption = "Title" }._value = "My Article"
        editor._get<TextArea>()._value = "The body of the article"
        editor._get<Button> { caption = "Save Article" }._click()
        val articles = Article.findAll()
        expect(1) { articles.size }
        expect("My Article") { articles[0].title }
        expect("The body of the article") { articles[0].text }
    }

    test("save not performed on invalid input") {
        val editor = ArticleEditor()
        editor.article = Article()
        val titleField = editor._get<TextField> { caption = "Title" }
        titleField._value = "Foo"
        editor._get<TextArea>()._value = "The body of the article"
        val saveArticle = editor._get<Button> { caption = "Save Article" }
        saveArticle._click()
        val articles = Article.findAll()
        expect(0) { articles.size }
        expect("There are invalid fields") { saveArticle.errorMessage.message }
        expect("length must be between 5 and 2147483647") { titleField.errorMessage.message }
    }

    test("go back") {
        val editor = ArticleEditor()
        editor._get<Button> { caption = "Back" } ._click()
        expectView<ArticlesView>()
    }
})