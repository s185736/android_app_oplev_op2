package com.project.oplevapp.data.dbTest

data class User (
    var userID: String = "",
    var firstName: String = "",
    var lastName: String = ""
) {
    override fun toString(): String {
            return "$firstName $lastName"
    }
}



