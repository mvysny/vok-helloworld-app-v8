package com.example.vok

import com.github.mvysny.dynatest.DynaNodeGroup
import com.github.mvysny.karibudsl.v8.autoDiscoverViews
import com.github.mvysny.kaributesting.v8.MockVaadin
import com.github.mvysny.kaributesting.v8._click
import com.github.mvysny.kaributesting.v8._get
import com.github.mvysny.kaributesting.v8._value
import com.github.vokorm.deleteAll
import com.vaadin.navigator.View
import com.vaadin.ui.Button
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField
import com.vaadin.ui.UI
import kotlin.test.expect

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

// @todo mavi remove when upgraded to KT 1.1.3
/**
 * Returns the current view (last view that was navigated to). Returns null if there is no current UI, or there is no
 * navigator, or the navigator's current view is null.
 */
val currentView: View? get() = UI.getCurrent()?.navigator?.currentView

/**
 * Expects that given [view] is the currently displayed view.
 */
fun <V: View> expectView(view: Class<V>) {
    @Suppress("UNCHECKED_CAST")
    (expect(view as Class<View>) { currentView?.javaClass })
}

/**
 * Expects that given view is the currently displayed view.
 */
inline fun <reified V: View> expectView() = expectView(V::class.java)
