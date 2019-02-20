package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.kaributesting.v8._get
import com.vaadin.ui.VerticalLayout
import kotlin.test.expect

class CommentsComponentTest : DynaTest({
    usingApp()

    test("smoke") {
        val component = CommentsComponent()
        expect(0) { component._get<VerticalLayout> { caption = "Comments" } .componentCount }
    }
})
