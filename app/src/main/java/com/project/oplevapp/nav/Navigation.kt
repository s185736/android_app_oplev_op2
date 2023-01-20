package com.project.oplevapp.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.*
import com.project.oplevapp.ui.screen.*
import com.project.oplevapp.ui.screen.profile.LoginPage
import com.project.oplevapp.ui.screen.idea_portal.actions.ModifyPortal
import com.project.oplevapp.ui.screen.idea_portal.actions.idea.PortalScreen
import com.project.oplevapp.ui.screen.profile.CreateAccount
import com.project.oplevapp.ui.screen.profile.Profile
import com.project.oplevapp.ui.screen.profile.ResetPassword
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.oplevapp.data.CountryRepository
import com.project.oplevapp.data.NotesRepository
import com.project.oplevapp.data.user.UserRepository
import com.project.oplevapp.model.Country
import com.project.oplevapp.ui.screen.country.*

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
        bottomBar = { BottomBar(navController = navController, navState = navState) },
        content = {
            NavHost( navController = navController, startDestination = Screen.LandingPage.route,) {
                composable(Screen.Profile.route) {
                    Profile(
                        navController = navController,
                        userRepository = userRepository
                    )
                }
                composable(Screen.ResetPassword.route) { ResetPassword(navController = navController) }
                composable(Screen.CountriesList.route) { CountriesList(navController) }
                composable(Screen.Country.route) {
                    //receiving data
                    val country =
                        navController.previousBackStackEntry?.savedStateHandle?.get<Country>("country")
                    if (country != null) {
                        CountryPage(country = country, navController = navController)
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

                composable(Screen.Login.route){ LoginPage(userRepository, navController) }
                composable(Screen.Note.route){ writeNotes(navController = navController, notesRepository ) }
                composable(Screen.AddCountry.route){ AddCountry(navController, countryRepository) }

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


                composable(Screen.TripShare.route){ ShareTrip(navController) }
            }
        }
    )
}