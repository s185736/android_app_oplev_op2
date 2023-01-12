package com.project.oplevapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.oplevapp.data.user.UserRepository
import com.project.oplevapp.nav.MainNavHost
import com.project.oplevapp.ui.screen.profile.CreateAccount
import com.project.oplevapp.ui.theme.OplevAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userRepository = UserRepository()
            OplevAppTheme {
                //MainNavHost()
                CreateAccount(userRepository = userRepository)
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


