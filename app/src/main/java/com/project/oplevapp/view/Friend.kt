package com.project.oplevapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.oplevapp.Screen

@Composable
fun FriendsList(
    navController: NavController
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(text = "Countries", Modifier.padding(20.dp), fontSize = 40.sp)
        Button(onClick = { navController.navigate(Screen.Country.route) }) {
            Text(text = "Denmark")
        }
    }
}