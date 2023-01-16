package com.project.oplevapp.data.user.repo

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.project.oplevapp.data.user.utils.ResultState
import com.project.oplevapp.data.user.User
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val authDB:FirebaseAuth
) : UserRepo {

    private val userState = mutableStateOf(User())
    override fun userCreate(user: User): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)

        authDB.createUserWithEmailAndPassword(
            user.email!!,
            user.password!!
        ).addOnCompleteListener {
            if(it.isSuccessful){
                trySend(ResultState.Success("Du har nu oprettet en bruger."))
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
            trySend(ResultState.Success("Du er nu logget på."))
            UpdateUserUI(true)
            Log.d("main", "current user id: ${authDB.currentUser?.uid}")
        }.addOnFailureListener {
            trySend(ResultState.Failure(it))
        }
        awaitClose {
            close()
        }
    }

    fun UpdateUserUI(isOn : Boolean) {
        userState.value = userState.value.copy(isSignedIn = isOn)
    }
}