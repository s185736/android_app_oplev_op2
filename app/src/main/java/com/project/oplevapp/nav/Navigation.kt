package com.project.oplevapp.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.data.CountryRepository
import com.project.oplevapp.model.Country
import com.project.oplevapp.ui.screen.*
import com.project.oplevapp.ui.screen.profile.CreateAccountScreen
import com.project.oplevapp.ui.screen.profile.LoginPage
import com.project.oplevapp.ui.screen.country.AddCountry
import com.project.oplevapp.ui.screen.country.CountriesList
import com.project.oplevapp.ui.screen.country.CountryPage
import com.project.oplevapp.ui.screen.country.EditCountry
import com.project.oplevapp.ui.screen.profile.Profile

@Composable
fun MainNavHost() {
    val items = listOf(Screen.Profile, Screen.TripList, Screen.Note)
    val navController = rememberNavController()
    val countryRepository = CountryRepository()

    val auth by lazy {
        Firebase.auth
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.White) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon!!, contentDescription = null, tint = Color.Black, modifier = Modifier.size(33.dp)) },
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
        NavHost(navController, startDestination = Screen.LandingPage.route, Modifier.padding(innerPadding)) {
            composable(Screen.Profile.route) { Profile() }
            composable(Screen.CountriesList.route) { CountriesList(navController) }
            composable(Screen.Country.route) {
                //receiving data
                val country = navController.previousBackStackEntry?.savedStateHandle?.get<Country>("country")
                if (country != null){
                    CountryPage(country = country, navController = navController)
                    println("Details page loaded successfully")
                }
                else{
                    println("No data in country")
                }
            }
            composable(Screen.EditCountry.route) {
                //receiving data
                val country = navController.previousBackStackEntry?.savedStateHandle?.get<Country>("country")
                if (country != null){
                    EditCountry(country = country, navController = navController, countryRepository = countryRepository)
                    println("Edit page loaded successfully")
                }
                else{
                    println("No data in country")
                }
            }
            composable(Screen.Login.route){ LoginPage(navController, auth) }
            composable(Screen.Note.route){ writeNotes(navController = navController) }
            composable(Screen.AddCountry.route){ AddCountry(navController, countryRepository) }

            composable(Screen.LandingPage.route){ LandingPage(navController) }

            composable(Screen.TripList.route){ TripListScreen(navController = navController, countryRepository = countryRepository) }
            composable(Screen.CreateAccount.route){ CreateAccountScreen(navController = navController, auth = auth) }



        }
    }
}