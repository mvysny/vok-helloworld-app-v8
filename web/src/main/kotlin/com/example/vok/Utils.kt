package com.example.vok

import com.github.vok.karibudsl.*
import com.vaadin.event.ShortcutAction
import com.vaadin.ui.Alignment
import com.vaadin.ui.UI
import com.vaadin.ui.Window

/**
 * Shows a simple yes-no confirmation dialog, with given [text] and [title]
 * @param text defaults to "Are you sure?"
 */
fun confirmDialog(text: String = "Are you sure?", title: String? = null, yesListener: ()->Unit) {
    val window = Window()
    window.apply {
        caption = title
        setSizeUndefined()
        isModal = true
        isResizable = false
        center()
        addCloseShortcut(ShortcutAction.KeyCode.ESCAPE)

        verticalLayout {
            setSizeUndefined(); defaultComponentAlignment = Alignment.MIDDLE_CENTER
            label(text)
            horizontalLayout {
                button("Yes") {
                    onLeftClick { yesListener(); window.close() }
                    setPrimary()
                }
                button("No") {
                    onLeftClick { window.close() }
                }
            }
        }
    }
    UI.getCurrent().addWindow(window)
}
