package com.project.oplevapp

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object FriendsList : Screen("friendslist", R.string.friends_list)
    object Country : Screen("country_page", R.string.friends_list)
    object EditCountry: Screen("edit_country", R.string.friends_list)
}