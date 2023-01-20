package com.project.oplevapp.model.interfaces

import com.project.oplevapp.model.data.utils.ResultState
import com.project.oplevapp.model.data.user.User
import kotlinx.coroutines.flow.Flow


interface UserRepo {
    fun userCreate(
        auth: User
    ) : Flow<ResultState<String>>

    fun userLogin(
        auth: User
    ) : Flow<ResultState<String>>
}