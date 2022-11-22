package com.project.oplevapp

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object CountriesList : Screen("countrieslist", R.string.country_list)
    object Country : Screen("country_page", R.string.country)
    object EditCountry: Screen("edit_country", R.string.edit_country)
    object Login: Screen ("login", R.string.login)
    object Note: Screen ("writenotes", R.string.notes)
    object AddCountry: Screen("add_country", R.string.add_country)
}