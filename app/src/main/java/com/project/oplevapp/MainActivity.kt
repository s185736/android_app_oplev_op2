package com.project.oplevapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material.Scaffold

import androidx.annotation.RequiresApi

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


import com.project.oplevapp.nav.MainNavHost
import com.project.oplevapp.ui.screen.profile.LoginPage
import com.project.oplevapp.ui.screen.profile.Profile
import com.project.oplevapp.ui.theme.OplevAppTheme


// AuthScreen virker kun hvis der står @AndroidEntryPoint som den gør nu
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            OplevAppTheme {
                MainNavHost()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OplevAppTheme {
        MainNavHost()
    }
}


