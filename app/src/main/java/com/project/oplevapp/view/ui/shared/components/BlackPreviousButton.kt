package com.project.oplevapp.view.ui.shared.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.oplevapp.R


@Composable
fun BlackPreviousButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        modifier = Modifier.height(67.dp).width(67.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
            contentDescription = "",
            modifier = Modifier.fillMaxSize()
        )
    }
}