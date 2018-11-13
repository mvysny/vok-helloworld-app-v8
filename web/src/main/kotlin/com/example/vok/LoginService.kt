package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.server.*
import com.vaadin.ui.*
import eu.vaadinonkotlin.vaadin8.Session
import eu.vaadinonkotlin.vaadin8.loginForm
import java.io.Serializable

data class User(val name: String) : Serializable

object LoginService {
    fun login(user: User) {
        Session[User::class] = user
        Page.getCurrent().reload()
    }
    val currentUser: User? get() = Session[User::class]
    fun logout() {
        VaadinSession.getCurrent().close()
        Page.getCurrent().reload()
    }
}

class LoginView : VerticalLayout() {
    init {
        setSizeFull()
        loginForm("Vaadin-on-Kotlin Sample App") {
            alignment = Alignment.MIDDLE_CENTER
            onLogin { username, password -> LoginService.login(User(username)) }
        }
    }
}
