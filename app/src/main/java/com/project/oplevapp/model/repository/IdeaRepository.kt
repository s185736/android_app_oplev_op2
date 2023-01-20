package com.project.oplevapp.model.repository

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.view.ui.screen.idea_portal.actions.MainActions
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Idea
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IdeaRepository : ViewModel() {

    fun saveIdea(idea: Idea, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        var db = Firebase.firestore.collection("ideaPortal")

        //db.collection("cities").set(city)
         //   .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
         //   .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
        try {
            if (idea.id != null) {
                db.document(idea.id.toString()).set(idea)
                    .addOnSuccessListener {
                     Toast.makeText(context, "Successfully saved.", Toast.LENGTH_SHORT).show()
                    }
            } else {
                db.add(idea)
                    .addOnSuccessListener {
                       Toast.makeText(context, "Successfully saved.", Toast.LENGTH_SHORT).show()
                    }
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun deleteData(
        id: String,
        context: Context,
    ) = CoroutineScope(Dispatchers.IO).launch{

        var db = Firebase.firestore
            .collection("ideaPortal")
            .document(id)

        try {
            db.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Successfully deleted data", Toast.LENGTH_SHORT).show()
                }
        }catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }


    fun getIdeas(mainActions: MainActions,
                 data: (MutableList<Idea>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
        var db = Firebase.firestore.collection("ideaPortal")
        val list = mutableListOf<Idea>()

        try {
            db.addSnapshotListener{snapshot, e ->
                if (snapshot != null) {
                    for (document in snapshot) {
                        Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                        val id = document.id as Int
                        val ideaTitle = document.data["title"] as String
                        val ideaSuggestionText = document.data["text"] as String
                        val ideaTimeCreated = document.data["time"] as Long
                        val ideaColorStatus = document.data["color"] as Int
                        CoroutineScope(Dispatchers.IO).launch {
                            mainActions.addIdea(
                                idea = Idea(
                                    id = id,
                                    ideaTitle = ideaTitle,
                                    ideaSuggestionText = ideaSuggestionText,
                                    ideaTimeCreated = ideaTimeCreated,
                                    ideaColorStatus = ideaColorStatus
                                )
                            )
                        }
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
    }
}

