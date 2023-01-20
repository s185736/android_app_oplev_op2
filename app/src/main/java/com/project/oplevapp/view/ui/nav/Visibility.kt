package com.project.oplevapp.view.ui.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.project.oplevapp.view.ui.theme.OplevAppTheme

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Visibility(navController: NavController, navState: MutableState<Boolean>) {
    OplevAppTheme {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // show= true, hide= false
        if (navBackStackEntry?.destination?.route == "reset_password") {
            false.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "profile") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "landingpage") {
            false.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "countrieslist") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "country_page") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "edit_country") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "login") {
            false.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "write_notes") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "add_country") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "trip_list") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "create_account") {
            false.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "idea_portal_screen") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "create_idea_screen") {
            true.also { navState.value = it }
        }
        else if (navBackStackEntry?.destination?.route == "trip_share") {
            true.also { navState.value = it }
        }
        else {}
    }
}