package com.example.vok

import com.github.mvysny.dynatest.DynaTest
import com.github.mvysny.karibudsl.v8.navigateToView
import com.github.mvysny.kaributesting.v8._expectOne

class WelcomeViewTest : DynaTest({
    usingApp()
    beforeEach { login() }

    test("smoke") {
        navigateToView<WelcomeView>()
        _expectOne<WelcomeView>()
    }
})