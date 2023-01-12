package com.project.oplevapp.data.user

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(): ViewModel() {
    fun saveUser(
        userData: UserData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{
        var db = Firebase.firestore.collection("testUsers")
        try {
            if (userData.userID != null){
                db.document(userData.userID).set(userData)
                    .addOnSuccessListener{ Toast.makeText(context,"Bruger tilføjet til database", Toast.LENGTH_SHORT).show()
                    }
            }
            else{
                db.add(userData)
                    .addOnSuccessListener{ Toast.makeText(context,"Bruger tilføjet til database", Toast.LENGTH_SHORT).show()}
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
        catch (e: Exception){
            Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
        }
    }

}