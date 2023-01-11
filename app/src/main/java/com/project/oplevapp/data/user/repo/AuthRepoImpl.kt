package com.project.oplevapp.data.user.repo

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.project.oplevapp.data.user.ResultState
import com.project.oplevapp.data.user.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val authdb:FirebaseAuth
) : AuthRepo {

    override fun userCreate(auth: User): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        authdb.createUserWithEmailAndPassword(
            auth.email!!,
            auth.password!!
        ).addOnCompleteListener {
            if(it.isSuccessful){
                trySend(ResultState.Success("User created successfully"))
                Log.d("main", "current user id: ${authdb.currentUser?.uid}")
            }
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }

        awaitClose {
            close()
        }
    }

    override fun userLogin(auth: User): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        authdb.signInWithEmailAndPassword(
            auth.email!!,
            auth.password!!
        ).addOnSuccessListener {
            trySend(ResultState.Success("login Successfully"))
            Log.d("main", "current user id: ${authdb.currentUser?.uid}")
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }
        awaitClose {
            close()
        }
    }

}