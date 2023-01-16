package com.project.oplevapp.ui.shared

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun ProgressIndicator() {
    Dialog(onDismissRequest = { }) {
        LinearProgressIndicator()
    }
}