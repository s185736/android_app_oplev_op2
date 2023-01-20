package com.project.oplevapp.view.ui.screen.idea_portal.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TextField(
    /*defining variables for BasicTextField*/
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = false,
    textStyle: TextStyle = TextStyle(),
    modifier: Modifier = Modifier,
    /*defining for Text.*/
    onFocusChange: (FocusState) -> Unit,
    ideaMessage: String,
    isVisible: Boolean = true
): Unit =
    Box(
    modifier = modifier, content = {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = singleLine,
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth().onFocusChanged {
                onFocusChange(it)
            }
        )
        when {
            isVisible -> Text(text = ideaMessage, style = textStyle, color = Color.DarkGray)
        }
    }
)