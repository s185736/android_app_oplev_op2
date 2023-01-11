package com.project.oplevapp.data.user.repo

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.project.oplevapp.data.user.utils.ResultState
import com.project.oplevapp.data.user.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authDB:FirebaseAuth
) : AuthRepo {

    override fun userCreate(user: User): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        authDB.createUserWithEmailAndPassword(
            user.email!!,
            user.password!!
        ).addOnCompleteListener {
            if(it.isSuccessful){
                trySend(ResultState.Success("User created successfully"))
                Log.d("main", "current user id: ${authDB.currentUser?.uid}")
            }
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    override fun userLogin(user: User): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        authDB.signInWithEmailAndPassword(
            user.email!!,
            user.password!!
        ).addOnSuccessListener {
            trySend(ResultState.Success("login Successfully"))
            Log.d("main", "current user id: ${authDB.currentUser?.uid}")
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }
        awaitClose {
            close()
        }
    }

}