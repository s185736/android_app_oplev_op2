package com.project.oplevapp.model.data.user

data class User (
    val email: String? = "",
    val password: String? = "",
    val isSignedIn: Boolean = false
)