package com.project.oplevapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.data.user.ui.AuthScreen1
import com.project.oplevapp.ui.MainNavHost
import com.project.oplevapp.ui.screen.profile.LoginPage
import com.project.oplevapp.ui.screen.profile.Profile
import com.project.oplevapp.ui.theme.OplevAppTheme
import dagger.hilt.android.AndroidEntryPoint

// AuthScreen virker kun hvis der står @AndroidEntryPoint som den gør nu
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            androidx.compose.material.Surface() {
                Scaffold {
                    AuthScreen1()
                }
            }
            /*
            OplevAppTheme {
                val navController = rememberNavController()
                val auth by lazy {
                    Firebase.auth
                }
               // LoginPage(navController, auth)
                MainNavHost()
            }

             */
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


