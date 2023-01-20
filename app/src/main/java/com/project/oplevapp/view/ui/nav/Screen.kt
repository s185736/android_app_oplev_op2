package com.project.oplevapp.view.ui.nav

import com.project.oplevapp.R

sealed class Screen(var route: String, var icon: Int, var title: String) {
    object ResetPassword : Screen("reset_password",  R.drawable.ic_baseline_near_me_24, "Nulstil Adgangskode")
    object Profile : Screen("profile", R.drawable.ic_baseline_near_me_24 /*R.drawable.settings_icon*/, "Profil")
    object LandingPage : Screen("landingpage",  R.drawable.ic_baseline_near_me_24, "Landing Page")
    object CountriesList : Screen("countrieslist",  R.drawable.ic_baseline_near_me_24, "Tur Liste")
    object Country : Screen("country_page", R.drawable.ic_baseline_near_me_24, "Opslagstavle")
    object EditCountry: Screen("edit_country",  R.drawable.ic_baseline_near_me_24, "Opdater Rejse")
    object Login: Screen("login",  R.drawable.ic_baseline_near_me_24, "Login")
    object Note: Screen("write_notes", R.drawable.ic_baseline_near_me_24, "Noter")
    object AddCountry: Screen("add_country", R.drawable.ic_baseline_near_me_24, "Tilføj Rejse")
    object TripList: Screen("trip_list", R.drawable.ic_baseline_near_me_24, "Home")
    object CreateAccount: Screen("create_account", R.drawable.ic_baseline_near_me_24, "Opret Konto")
    object IdeaScreen: Screen("idea_portal_screen", R.drawable.ic_baseline_near_me_24, "Ide Portalen")
    object ModifyInIdeaMessageScreen: Screen("create_idea_screen", R.drawable.ic_baseline_near_me_24, "Opret Ide")
    object TripShare: Screen("trip_share", R.drawable.ic_baseline_near_me_24, "Del Rejser")
}