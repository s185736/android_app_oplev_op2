package com.project.oplevapp.nav

import androidx.annotation.StringRes
import com.project.oplevapp.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object CountriesList : Screen("countrieslist", R.string.country_list)
    object Country : Screen("country_page", R.string.country)
    object EditCountry: Screen("edit_country", R.string.edit_country)
    object Login: Screen("login", R.string.login)
    object Note: Screen("writenotes", R.string.notes)
    object AddCountry: Screen("add_country", R.string.add_country)
    object TripList: Screen("trip_list", R.string.start_page)
    object CreateAccount: Screen("create_account", R.string.create_account)
    object IdeaScreen: Screen("idea_portal_screen", R.string.idea_portal_screen)
    object ModifyInIdeaMessageScreen: Screen("create_idea_screen", R.string.create_idea_screen)

    object User: Screen("user", R.string.start_page)




}