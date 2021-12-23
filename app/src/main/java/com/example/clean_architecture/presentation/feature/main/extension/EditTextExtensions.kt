package com.example.clean_architecture.presentation.feature.main.extension

import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.setOnSearchAction(search: (view: View, text: String) -> Unit) {
    setOnEditorActionListener { view: View, actionId: Int, _: KeyEvent? ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            search(view, text.toString())
            true
        } else {
            false
        }
    }

    setOnKeyListener { view: View, keyCode: Int, event: KeyEvent ->
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            search(view, text.toString())
            true
        } else {
            false
        }
    }
}