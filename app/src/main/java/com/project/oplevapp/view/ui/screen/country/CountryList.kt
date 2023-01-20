package com.project.oplevapp.view.ui.screen.country

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
import com.project.oplevapp.view.ui.nav.Screen

@Composable
fun CountryList(
    navController: NavController
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(text = "Oplevelser", Modifier.padding(20.dp), fontSize = 40.sp)
        Button(onClick = { navController.navigate(Screen.TripList.route) }) {
            Text(text = "Tur liste")
        }
        Button(onClick = { navController.navigate(Screen.AddCountry.route) }) {
            Text(text = "Add country")
        }
    }
}