package com.project.oplevapp.model.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.model.data.NotesInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesRepository : ViewModel() {

    fun saveNotes(
        notes: NotesInfo,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch {
        var db = Firebase.firestore.collection("notes")
        try {
            if (notes.id != null) {
                db.document(notes.id).set(notes)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Successfully saved Notes", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                db.add(notes)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Successfully saved Notes", Toast.LENGTH_SHORT)
                            .show()
                    }
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}