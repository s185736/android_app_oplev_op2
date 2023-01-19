package com.project.oplevapp.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
sealed class Screen(var route: String, var icon: ImageVector, var title: String) {
    object ResetPassword : Screen("reset_password", Icons.Default.Home, "Nulstil Adgangskode")
    object Profile : Screen("profile", Icons.Default.Person, "Profil")
    object LandingPage : Screen("landingpage", Icons.Default.Home, "Landing Page")
    object CountriesList : Screen("countrieslist", Icons.Default.Home, "Countries List")
    object Country : Screen("country_page",Icons.Default.Home, "Country Page")
    object EditCountry: Screen("edit_country", Icons.Default.Home, "Edit Trip")
    object Login: Screen("login", Icons.Default.Home, "Login")
    object Note: Screen("write_notes", Icons.Default.Notes, "Noter")
    object AddCountry: Screen("add_country", Icons.Default.Home, "Add Trip")
    object TripList: Screen("trip_list", Icons.Default.Home, "Shareboard")
    object CreateAccount: Screen("create_account", Icons.Default.Home, "Opret Konto")
    object IdeaScreen: Screen("idea_portal_screen", Icons.Default.Home, "Ide Portalen")
    object ModifyInIdeaMessageScreen: Screen("create_idea_screen", Icons.Default.Home, "Opret Ide")
    object TripShare: Screen("trip_share", Icons.Default.Home, "Share Trip")
}