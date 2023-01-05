package com.project.oplevapp.ui.screen

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.oplevapp.Screen
import com.project.oplevapp.ui.screen.idea_portal.actions.ModifyPortal
import com.project.oplevapp.ui.screen.idea_portal.actions.idea.PortalScreen
import com.project.oplevapp.ui.theme.OplevAppTheme
import com.project.oplevapp.ui.theme.OplevDarkBlue
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class IdeaPortalActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(instance: Bundle?) {
        super.onCreate(instance)
        setContent {
            OplevAppTheme {
                Surface(color = OplevDarkBlue) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.IdeaScreen.route) {
                        composable(route = Screen.IdeaScreen.route) { PortalScreen(navController = navController) }
                        composable(
                            route = Screen.ModifyInIdeaMessageScreen.route,
                            arguments = listOf(navArgument(name = "ideaId") { type = NavType.IntType
                                defaultValue = -1 },
                                navArgument(name = "ideaColor") { type = NavType.IntType
                                    defaultValue = -1 },
                            )) {
                            val color = (it.arguments?.getInt("ideaColor") ?: -1).also {
                                ModifyPortal(navController = navController, ideaColor = it)
                            }
                        }
                    }
                }
            }
        }
    }
}