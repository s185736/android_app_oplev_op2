package com.project.oplevapp.view.ui.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import com.project.oplevapp.view.ui.screen.*
import com.project.oplevapp.view.ui.screen.profile.LoginPage
import com.project.oplevapp.view.ui.screen.idea_portal.actions.ModifyPortal
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.PortalScreen
import com.project.oplevapp.view.ui.screen.profile.CreateAccount
import com.project.oplevapp.view.ui.screen.profile.Profile
import com.project.oplevapp.view.ui.screen.profile.ResetPassword
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.oplevapp.model.data.Country
import com.project.oplevapp.model.repository.CountryRepository
import com.project.oplevapp.model.repository.NotesRepository
import com.project.oplevapp.model.repository.UserRepository
import com.project.oplevapp.view.ui.screen.country.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MainNavHost() {
    val navState = rememberSaveable { (mutableStateOf(true)) }
    val countryRepository = CountryRepository()
    val userRepository = UserRepository()
    val notesRepository = NotesRepository()
    val navController = rememberNavController()

    Visibility(
        navController = navController,
        navState = navState
    )

    Scaffold(
        bottomBar = { BottomBar(navController = navController, navState = navState) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.LandingPage.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.Profile.route) {
                Profile(
                    navController = navController,
                    userRepository = userRepository
                )
            }
            composable(Screen.ResetPassword.route) { ResetPassword(navController = navController) }
            composable(Screen.CountriesList.route) { CountryList(navController) }
            composable(Screen.Country.route) {
                //receiving data
                val country =
                    navController.previousBackStackEntry?.savedStateHandle?.get<Country>("country")
                if (country != null) {
                    Shareboard(country = country, navController = navController)
                    println("Details page loaded successfully")
                } else {
                    println("No data in country")
                }
            }

            composable(Screen.EditCountry.route) {
                //receiving data
                val country =
                    navController.previousBackStackEntry?.savedStateHandle?.get<Country>("country")
                if (country != null) {
                    EditCountry(
                        country = country,
                        navController = navController,
                        countryRepository = countryRepository
                    )
                    println("Edit page loaded successfully")
                } else {
                    println("No data in country")
                }
            }

            composable(Screen.Login.route) { LoginPage(navController) }
            composable(Screen.Note.route) {
                WriteNote(
                    navController = navController,
                    notesRepository
                )
            }
            composable(Screen.AddCountry.route) { AddTrip(navController, countryRepository) }

            composable(Screen.LandingPage.route) { LandingPage(navController) }

            composable(Screen.TripList.route) {
                TripListScreen(
                    navController = navController,
                    countryRepository = countryRepository
                )
            }
            composable(Screen.CreateAccount.route) {
                CreateAccount(navController = navController, userRepository = userRepository)
            }
            composable(Screen.IdeaScreen.route) { PortalScreen(navController = navController) }
            composable(
                route = Screen.ModifyInIdeaMessageScreen.route + "?ideaId={ideaId}&ideaColor={ideaColor}",
                arguments = listOf(
                    navArgument(name = "ideaId") {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                    navArgument(name = "ideaColor") {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                )
            ) {
                val color = it.arguments?.getInt("ideaColor") ?: -1
                ModifyPortal(navController = navController, ideaColor = color)
            }


            composable(Screen.TripShare.route) { ShareTrip(navController) }
        }
    }

}