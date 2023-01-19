package com.project.oplevapp.data.user

data class User (
    val email: String? = "",
    val password: String? = "",
    val isSignedIn: Boolean = false
)