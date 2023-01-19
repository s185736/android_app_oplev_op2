package com.project.oplevapp.data.user.repo.auth

import com.project.oplevapp.data.user.utils.ResultState
import com.project.oplevapp.data.user.User
import kotlinx.coroutines.flow.Flow


interface UserRepo {
    fun userCreate(
        auth: User
    ) : Flow<ResultState<String>>

    fun userLogin(
        auth: User
    ) : Flow<ResultState<String>>
}