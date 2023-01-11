package com.project.oplevapp.data.user.ui

import androidx.lifecycle.ViewModel
import com.project.oplevapp.data.user.User
import com.project.oplevapp.data.user.repo.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo:AuthRepo
): ViewModel(){

    fun createUser(user: User) = repo.createUser(user)

    fun loginUser(user: User) = repo.loginUser(user)


}