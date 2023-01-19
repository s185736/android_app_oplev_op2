package com.project.oplevapp.data.user

import androidx.lifecycle.ViewModel
import com.project.oplevapp.data.user.repo.auth.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepo
): ViewModel(){

    fun userCreate(user: User) = repository.userCreate(user)

    fun userLogin(user: User) = repository.userLogin(user)


}