package com.project.oplevapp.ui

import CountryPage
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.Screen
import com.project.oplevapp.data.Denmark
import com.project.oplevapp.ui.screen.*
import com.project.oplevapp.ui.screen.profile.CreateAccountScreen
import com.project.oplevapp.ui.screen.profile.LoginPage

@Composable
fun MainNavHost() {
    val items = listOf(Screen.Login, Screen.TripList, Screen.Note)
    val navController = rememberNavController()

    val auth by lazy {
        Firebase.auth
    }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Login.route, Modifier.padding(innerPadding)) {
            composable(Screen.Profile.route) { Profile(navController) }
            composable(Screen.CountriesList.route) { CountriesList(navController) }
            composable(Screen.Country.route) { CountryPage(country = Denmark, navController = navController) }
            composable(Screen.EditCountry.route) { EditCountry(country = Denmark, navController = navController) }
            composable(Screen.Login.route){ LoginPage(navController, auth) }
            composable(Screen.Note.route){ writeNotes(navController = navController) }
            composable(Screen.AddCountry.route){ AddCountry(navController) }

            composable(Screen.TripList.route){ TripListScreen(navController = navController)}
            composable(Screen.CreateAccount.route){ CreateAccountScreen(navController = navController, auth = auth) }



        }
    }
}