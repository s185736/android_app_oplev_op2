package com.project.oplevapp.model.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.model.data.Country
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryRepository : ViewModel() {

    fun saveCountry(
        country: Country,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        var db = Firebase.firestore.collection("countries")
        try {
            if (country.id != null) {
                db.document(country.id).set(country)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Successfully saved country", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                db.add(country)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Successfully saved country", Toast.LENGTH_SHORT)
                            .show()
                    }
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteData(
        id: String,
        context: Context,
    ) = CoroutineScope(Dispatchers.IO).launch {

        var db = Firebase.firestore
            .collection("countries")
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


    fun getCountries(
        data: (MutableList<Country>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        var db = Firebase.firestore.collection("countries")
        val list = mutableListOf<Country>()

        try {
            db.addSnapshotListener { snapshot, e ->
                if (snapshot != null) {
                    for (document in snapshot) {

                        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                        val id = document.id
                        val countryFromDb = document.data["country"] as String
                        val city = document.data["city"] as String
                        val departureDate = document.data["departureDate"] as String
                        val returnDate = document.data["returnDate"] as String
                        val imageUrl = document.data["imageUrl"] as String
                        val info = document.data["info"] as String
                        list.add(
                            Country(
                                id = id,
                                city = city,
                                country = countryFromDb,
                                departureDate = departureDate,
                                returnDate = returnDate,
                                imageUrl = imageUrl,
                                info = info
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
}

