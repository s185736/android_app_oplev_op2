package com.project.oplevapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.ui.MainNavHost
import com.project.oplevapp.ui.screen.profile.LoginPage
import com.project.oplevapp.ui.screen.profile.Profile
import com.project.oplevapp.ui.theme.OplevAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OplevAppTheme {
                val navController = rememberNavController()
                val auth by lazy {
                    Firebase.auth
                }
               // LoginPage(navController, auth)
                MainNavHost()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OplevAppTheme {
        val navController = rememberNavController()
        val auth by lazy {
            Firebase.auth
        }
        LoginPage(navController, auth)
    }
}