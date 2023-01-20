package com.project.oplevapp.viewmodel

import androidx.lifecycle.ViewModel
import com.project.oplevapp.model.data.user.User
import com.project.oplevapp.model.interfaces.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepo
): ViewModel(){

    fun userCreate(user: User) = repository.userCreate(user)

    fun userLogin(user: User) = repository.userLogin(user)


}