package com.project.oplevapp.data.user

val l: Long  = 1

data class UserData(
    val userID: String? = "",
    val email: String = "",
    val password: String = "",
    val name: String = "",
    // skal fikses til at være int senere
    val number: String = ""
)