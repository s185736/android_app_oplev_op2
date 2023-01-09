package com.project.oplevapp.data

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.model.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryRepository(): ViewModel() {

fun saveCountry(
    country: Country,
    context: Context
) = CoroutineScope(Dispatchers.IO).launch{
        var db = Firebase.firestore.collection("countries")
        try {
            db.add(country)
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully saved country", Toast.LENGTH_SHORT)
                }
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT)
        }
   }

    fun deleteData(
        userId: String,
        context: Context,
        navController: NavController
    ) = CoroutineScope(Dispatchers.IO).launch{

        var firestoreRef = Firebase.firestore
            .collection("user")
            .document(userId)

        try {
            firestoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully deleted data", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }





    fun getCountries(
        data: (MutableList<Country>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{

        var db = Firebase.firestore.collection("countries")
        val list = mutableListOf<Country>()

        try {
            db.addSnapshotListener{snapshot, e ->
                if(snapshot != null){
                    for (document in snapshot){

                        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                        val countryFromDb = document.data["country"] as String
                        val city = document.data["city"] as String
                        val departureDate = document.data["departureDate"] as String
                        val returnDate = document.data["returnDate"] as String
                        val imageUrl = document.data["imageUrl"] as String
                        val info = document.data["info"] as String
                        list.add(
                            Country(
                                city = city,
                                country = countryFromDb,
                                departureDate = departureDate,
                                returnDate = returnDate,
                                imageUrl = imageUrl,
                                info = info)
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
        }catch (e: Exception){
            Log.w(ContentValues.TAG, "Error getting documents.", e)
        }

        /*
        try {
            db.get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                        val countryFromDb = document.data["country"] as String
                        val city = document.data["city"] as String
                        val departureDate = document.data["departureDate"] as String
                        val returnDate = document.data["returnDate"] as String
                        val imageUrl = document.data["imageUrl"] as String
                        val info = document.data["info"] as String
                        list.add(
                            Country(
                                city = city,
                                country = countryFromDb,
                                departureDate = departureDate,
                                returnDate = returnDate,
                                imageUrl = imageUrl,
                                info = info)
                        )
                        data(list)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents.", exception)
                }

        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

         */
    }
}

