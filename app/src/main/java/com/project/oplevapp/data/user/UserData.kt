package com.project.oplevapp.data.user

val l: Long  = 1

data class UserData(
    val userID: String? = "",
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val number: String = ""
)