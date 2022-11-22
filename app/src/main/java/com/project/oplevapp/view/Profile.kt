package com.project.oplevapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.oplevapp.Screen

@Composable
fun Profile(
    navController: NavController
) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(text = "Profile", Modifier.padding(20.dp), fontSize = 40.sp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Button(onClick = { navController.navigate(Screen.Login.route)
            }) {
                Text(text = "Login")
            }
            Button(onClick = {
                navController.navigate(Screen.CreateAccount.route)
            }) {
                Text(text = "Opret login")

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview(){
    Profile(navController = rememberNavController())
}