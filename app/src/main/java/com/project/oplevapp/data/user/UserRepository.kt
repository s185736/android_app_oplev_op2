package com.project.oplevapp.data.user

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.nav.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(): ViewModel() {
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
                        Toast.makeText(context, "Bruger tilføjet til database", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                db.add(userData)
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            "Bruger tilføjet til database",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
        /*
        val fireStoreRef = Firebase.firestore
            .collection("users")
            .document(user.userID)

        try {
            fireStoreRef.set(user)
                .addOnSuccessListener {
                    Toast.makeText(context,"Fuldendt",Toast.LENGTH_SHORT).show()
                }
        }

         */
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
                        Toast.makeText(context, "Brugeren er blevet opdateret.", Toast.LENGTH_SHORT)
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


    fun getUsers(
        data: (MutableList<UserData>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        var db = Firebase.firestore.collection("users")
        val list = mutableListOf<UserData>()

        try {
            db.addSnapshotListener { snapshot, e ->
                if (snapshot != null) {
                    for (document in snapshot) {

                        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                        val id = document.id
                        val name = document.data["name"] as String
                        val password = document.data["password"] as String
                        val email = document.data["email"] as String
                        val number = document.data["number"] as String
                        list.add(
                            UserData(
                                userID = id,
                                password = password,
                                name = name,
                                email = email,
                                number = number
                            )
                        )
                        data(list)
                    }
                } else {
                    if (e != null) {
                        println(e.message)
                        Log.w(ContentValues.TAG, "Error getting documents.", e)
                    }
                }
            }
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error getting documents.", e)
        }
    }

    fun deleteData(
        id: String,
        context: Context,
    ) = CoroutineScope(Dispatchers.IO).launch {

        var db = Firebase.firestore
            .collection("users")
            .document(id)

        try {
            db.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully deleted data", Toast.LENGTH_SHORT).show()
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
        navController.navigate(Screen.LandingPage.route)
        Toast.makeText(context, "Din bruger er nu slettet fra databasen.", Toast.LENGTH_SHORT).show()
    }

    fun signUserOut(navController: NavController, context: Context) {
        userState.value = userState.value.copy(isSignedIn = false)
        FirebaseAuth.getInstance().signOut()
        navController.navigate(Screen.Login.route)
        Toast.makeText(context, "Du er nu logget ud.", Toast.LENGTH_SHORT).show()
    }
}