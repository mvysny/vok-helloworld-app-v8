package com.example.vok

import com.github.mvysny.karibudsl.v8.*
import com.vaadin.server.*
import com.vaadin.ui.*
import eu.vaadinonkotlin.vaadin8.Session
import eu.vaadinonkotlin.vaadin8.loginForm
import java.io.Serializable

data class User(val name: String) : Serializable

class LoginService : Serializable {
    fun login(username: String, password: String): Boolean {
        currentUser = User(username)
        Page.getCurrent().reload()
        return true
    }
    var currentUser: User? = null
    private set

    fun logout() {
        VaadinSession.getCurrent().close()
        Page.getCurrent().reload()
    }

    val isLoggedIn get() = currentUser != null
}

val Session.loginService: LoginService get() = getOrPut { LoginService() }

class LoginView : Composite() {
    private val root = verticalLayout {
        setSizeFull()
        loginForm("Vaadin-on-Kotlin Sample App") {
            alignment = Alignment.MIDDLE_CENTER
            onLogin { username, password ->
                if (!Session.loginService.login(username, password)) {
                    usernameField.componentError = UserError("The user does not exist or invalid password")
                }
            }
        }
    }
}
