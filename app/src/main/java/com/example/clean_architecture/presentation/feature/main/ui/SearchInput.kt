package com.example.clean_architecture.presentation.feature.main.ui

import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SearchInput(
    text: String,
    label: String,
    onValueChanged: (text: String) -> Unit,
    onActionSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChanged,
        label = { Text(text = label) },
        singleLine = true,
        modifier = modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .onKeyEvent { handleActionEnter(it, onActionSearch) },
        keyboardActions = KeyboardActions {
            onActionSearch()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        )
    )
}

private fun handleActionEnter(
    keyEvent: KeyEvent,
    onActionSearch: () -> Unit
): Boolean {
    if (keyEvent.nativeKeyEvent.keyCode == KEYCODE_ENTER) {
        onActionSearch()
        return true
    }
    return false
}