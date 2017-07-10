package com.example.vok

import com.github.vok.framework.Session
import com.github.vok.karibudsl.*
import com.vaadin.icons.VaadinIcons
import com.vaadin.server.*
import com.vaadin.ui.*
import com.vaadin.ui.themes.ValoTheme
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

class LoginForm : VerticalLayout() {
    private lateinit var username: TextField
    private lateinit var password: TextField
    init {
        setSizeFull()
        panel {
            w = 500.px; alignment = Alignment.MIDDLE_CENTER
            verticalLayout {
                w = fillParent
                horizontalLayout {
                    w = fillParent
                    label("Welcome") {
                        alignment = Alignment.BOTTOM_LEFT
                        addStyleNames(ValoTheme.LABEL_H4, ValoTheme.LABEL_COLORED)
                    }
                    label("Vaadin-on-Kotlin Sample App") {
                        alignment = Alignment.BOTTOM_RIGHT; styleName = ValoTheme.LABEL_H3; expandRatio = 1f
                    }
                }
                horizontalLayout {
                    w = fillParent
                    username = textField("Username") {
                        expandRatio = 1f; w = fillParent
                        icon = VaadinIcons.USER; styleName = ValoTheme.TEXTFIELD_INLINE_ICON
                    }
                    password = passwordField("Password") {
                        expandRatio = 1f; w = fillParent
                        icon = VaadinIcons.LOCK; styleName = ValoTheme.TEXTFIELD_INLINE_ICON
                    }
                    button("Sign In") {
                        alignment = Alignment.BOTTOM_RIGHT; setPrimary()
                        onLeftClick { login() }
                    }
                }
            }
        }
    }

    private fun login() {
        LoginService.login(User(username.value))
    }
}
