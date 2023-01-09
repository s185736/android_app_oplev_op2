/*Source: https://firebase.google.com/docs/database/android/read-and-write*/
package com.project.oplevapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.oplevapp.ui.screen.IdeaPortal
import com.project.oplevapp.ui.screen.IdeaPortalActivity
import com.project.oplevapp.ui.screen.idea_portal.IdeaApplication
import com.project.oplevapp.ui.screen.profile.Profile
import com.project.oplevapp.ui.theme.OplevAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(instance: Bundle?) {
        super.onCreate(instance)
        setContent {
            OplevAppTheme {
                /* val navController = rememberNavController()
                val auth by lazy {
                    Firebase.auth
                }
                LoginPage(navController, auth)*/
                Profile()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OplevAppTheme {
        Profile()
    }
}