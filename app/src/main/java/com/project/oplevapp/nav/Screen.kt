package com.project.oplevapp.nav

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.project.oplevapp.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector?) {

    object ResetPassword : Screen("Nulstil Adgangskode", R.string.resetpassword, null)

    object Profile : Screen("profile", R.string.profile, Icons.Default.Person)

    object LandingPage : Screen("landingpage", R.string.landingpage, null)

    object CountriesList : Screen("countrieslist", R.string.country_list, null)
    object Country : Screen("country_page", R.string.country, null)
    object EditCountry: Screen("edit_country", R.string.edit_country, null)
    object Login: Screen("login", R.string.login, Icons.Default.Login)
    object Note: Screen("writenotes", R.string.notes, Icons.Default.Notes)
    object AddCountry: Screen("add_country", R.string.add_country, null)
    object TripList: Screen("trip_list", R.string.start_page, Icons.Default.Home)
    object CreateAccount: Screen("create_account", R.string.create_account, null)
    object IdeaScreen: Screen("idea_portal_screen", R.string.idea_portal_screen, null)
    object ModifyInIdeaMessageScreen: Screen("create_idea_screen", R.string.create_idea_screen, null)

    object User: Screen("user", R.string.start_page, null)

}