package com.project.oplevapp.model.repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.model.data.user.User
import com.project.oplevapp.model.data.user.UserData
import com.project.oplevapp.view.ui.nav.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository : ViewModel() {
    val db = FirebaseFirestore.getInstance()
    val userState = mutableStateOf(User())

    fun saveUser(
        userData: UserData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        var db = Firebase.firestore.collection("users")
        try {
            if (userData.userID != null) {
                db.document(userData.userID).set(userData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Profilen er tilføjet til databasen", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                Toast.makeText(context, "Profilen kunne ikke tilføjes til databasen", Toast.LENGTH_SHORT)
            }
        }
        catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun updateUser(userData: UserData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        var db = Firebase.firestore.collection("users")
        try {
            if (userData.userID != null) {
                db.document(userData.userID).set(userData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Din profil er blevet opdateret.", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                Toast.makeText(context, "Handlingen mislykkedes.", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun getUser(
        userID: String,
        context: Context,
        data: (UserData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        var db = Firebase.firestore
            .collection("users")
            .document(userID)
        try {
            db.get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        var userData = it.toObject<UserData>()!!
                        data(userData)
                    } else {
                        Toast.makeText(context, "Ingen bruger data fundet", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteUser(navController: NavController, context: Context) {
        userState.value = userState.value.copy(isSignedIn = false) //remove this
        FirebaseAuth.getInstance().currentUser?.uid?.let {
            db.collection("users").document(it).delete()
        }
        val user = Firebase.auth.currentUser!!
        user.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User account deleted.")
                }
            }
        System.out.println("Successfully deleted user.")
        navController.navigate(Screen.LandingPage.route)
        Toast.makeText(context, "Din bruger er nu slettet fra databasen.", Toast.LENGTH_SHORT).show()
    }

    fun signUserOut(navController: NavController, context: Context) {
        FirebaseAuth.getInstance().signOut()
        userState.value = userState.value.copy(isSignedIn = false)
        navController.navigate(Screen.Login.route)
        Toast.makeText(context, "Du er nu logget ud.", Toast.LENGTH_SHORT).show()
        //act.startActivity(Intent.makeRestartActivityTask(act?.intent?.component))
    }

    fun ResetPassword() {
        userState.value.email?.let {
            Firebase.auth.sendPasswordResetEmail(it)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Nulstilling af kodeord er sendt til mailen.")
                    }
                }
        }
    }
}