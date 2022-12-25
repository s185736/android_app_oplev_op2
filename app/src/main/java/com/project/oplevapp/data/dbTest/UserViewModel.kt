package com.project.oplevapp.data.dbTest

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class UserViewModel(viewModel: UserViewModel) {
    private lateinit var firestore: FirebaseFirestore

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun save(user: User) {
        val document = firestore.collection("users").document()
        user.userID = document.id
        val handle = document.set(user)
        handle.addOnSuccessListener{Log.e("Firebase,", "Document saved")}
        handle.addOnFailureListener { Log.e("Firebase,", "Save failed $it ")}
    }
}