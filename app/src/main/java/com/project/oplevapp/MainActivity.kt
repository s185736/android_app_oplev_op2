package com.project.oplevapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.oplevapp.nav.MainNavHost
import com.project.oplevapp.ui.screen.profile.Profile
import com.project.oplevapp.ui.theme.OplevAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OplevAppTheme {
                Profile()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OplevAppTheme {
        MainNavHost()
    }
}