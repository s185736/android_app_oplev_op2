package com.project.oplevapp.data.user.repo

import com.project.oplevapp.data.user.ResultState
import com.project.oplevapp.data.user.User
import kotlinx.coroutines.flow.Flow


interface AuthRepo {
    fun userCreate(
        auth: User
    ) : Flow<ResultState<String>>

    fun userLogin(
        auth: User
    ) : Flow<ResultState<String>>
}