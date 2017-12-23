package com.example.vok

import com.github.vok.karibudsl.autoViewProvider
import com.github.vok.karibudsl.isMargin
import com.vaadin.annotations.Push
import com.vaadin.annotations.Theme
import com.vaadin.annotations.Title
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.PushStateNavigation
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewDisplay
import com.vaadin.server.Page
import com.vaadin.server.VaadinRequest
import com.vaadin.shared.Position
import com.vaadin.shared.ui.ui.Transport
import com.vaadin.ui.Component
import com.vaadin.ui.Notification
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.ValoTheme
import org.slf4j.LoggerFactory

/**
 * The Vaadin UI which demoes all the features. If not familiar with Vaadin, please check out the Vaadin tutorial first.
 * @author mvy
 */
@Theme("mytheme")
@Title("Vaadin On Kotlin")
@Push(transport = Transport.WEBSOCKET_XHR)
@PushStateNavigation
class MyUI : UI() {

    private val content = Content()

    override fun init(request: VaadinRequest?) {
        if (LoginService.currentUser == null) {
            setContent(LoginForm())
            return
        }
        setContent(content)
        navigator = Navigator(this, content as ViewDisplay)
        navigator.addProvider(autoViewProvider)
        setErrorHandler { e ->
            log.error("Vaadin UI uncaught exception ${e.throwable}", e.throwable)
            // when the exception occurs, show a nice notification
            Notification("Oops", "An error occurred, and we are really sorry about that. Already working on the fix!", Notification.Type.ERROR_MESSAGE).apply {
                styleName = "${ValoTheme.NOTIFICATION_CLOSABLE} ${ValoTheme.NOTIFICATION_ERROR}"
                position = Position.TOP_CENTER
                show(Page.getCurrent())
            }
        }
    }

    companion object {
        @JvmStatic
        private val log = LoggerFactory.getLogger(MyUI::class.java)
    }
}

private class Content: VerticalLayout(), ViewDisplay {
    init {
        setSizeFull(); isMargin = false
    }
    override fun showView(view: View?) {
        removeAllComponents()
        addComponent(view as Component)
    }
}
