package com.example.vok

import com.github.mvysny.dynatest.DynaNodeGroup
import com.github.mvysny.karibudsl.v8.autoDiscoverViews
import com.github.mvysny.kaributesting.v8.MockVaadin
import com.github.mvysny.kaributesting.v8._click
import com.github.mvysny.kaributesting.v8._get
import com.github.mvysny.kaributesting.v8._value
import com.github.vokorm.deleteAll
import com.vaadin.ui.Button
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField

fun DynaNodeGroup.usingApp() {
    beforeGroup { Bootstrap().contextInitialized(null) }
    afterGroup { Bootstrap().contextDestroyed(null) }
    fun cleanDatabase() {
        Comment.deleteAll()
        Article.deleteAll()
    }
    beforeEach { cleanDatabase() }
    afterEach { cleanDatabase() }

    beforeGroup { autoDiscoverViews("com.example") }
    beforeEach { MockVaadin.setup({ MyUI() }) }
    afterEach { MockVaadin.tearDown() }
}

fun login() {
    _get<TextField> { caption = "Username" }._value = "admin"
    _get<PasswordField> { caption = "Password" }._value = "admin"
    _get<Button> { caption = "Sign In" }._click()
}
