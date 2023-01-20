package com.project.oplevapp.view.ui.screen

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.model.repository.NotesRepository
import com.project.oplevapp.model.data.NotesInfo

@Composable
fun WriteNote(navController: NavController, notesRepository: NotesRepository) {
    val content = LocalContext.current
    var db = Firebase.firestore.collection("notes")

    var notesId by remember { mutableStateOf("") }
    var noteWriting by remember { mutableStateOf("") }

    var isLoading by remember {
        mutableStateOf(true)
    }

        NoteHeader()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 0.dp, top = 75.dp),
        ) {

            val notesToSave = NotesInfo(
                id = notesId,
                text = noteWriting
            )

            if (!isLoading) {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                if (notesToSave.text != "") {
                                    notesRepository.saveNotes(notesToSave, content)
                                }
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Default.Save,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            },
                            backgroundColor = Color(0xFF053667)
                        )
                    },
                    modifier = Modifier.padding()

                ) {
                    // Write notes

                    MyNotesField(
                        text = noteWriting,
                        textSize = 15,
                        onValueChange = { noteWriting = it },
                        placeHolder = "Skriv her",
                        width = 300,
                        height = 51,
                        KeyboardType.Text,
                        visualTransformation = VisualTransformation.None,
                        Color.DarkGray,
                        Color.LightGray,
                        Color.Gray,
                        )
                }
            }
            else{
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator()

                    try {
                        db.addSnapshotListener{snapshot, e ->
                            if(snapshot != null){
                                for (document in snapshot){
                                println("WORKING")
                                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                                    val id = document.id
                                    val notes = document.data["text"] as String

                                    notesId = id
                                    noteWriting = notes
                                }
                                isLoading = false

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
    }
}

@Composable
fun NoteHeader(){
    Box(
        modifier = Modifier
            .background(
                Color.White
            )
            .fillMaxSize()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 10.dp)
    ) {
        Row(

            Modifier
                .padding(0.dp)
                .height(45.dp),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            //  BlackPreviousButton({})
            Spacer(modifier = Modifier.padding(55.dp))
            Text(
                text = "Notesbog",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Text(
            text = "Min Personlige Noter",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

    }


}

@Composable
fun MyNotesField(
    text: String,
    textSize: Int,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    width: Int,
    height: Int,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    myTextColor: Color,
    backgroundColor: Color,
    placeHolderColor: Color
) {

    Surface(
        modifier = Modifier.padding(top = 55.dp,start = 20.dp, end = 20.dp, bottom = 55.dp),
        color = Color.White,
        shape = RoundedCornerShape(15),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {


            TextField(
                value = text,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.copy(color = myTextColor),
                placeholder = {
                    Text(
                        text = placeHolder,
                        fontSize = textSize.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Left,
                        color = placeHolderColor,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 20.dp, bottom = 20.dp)

                    )

                },

                visualTransformation = visualTransformation,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),

                modifier = Modifier.fillMaxSize(),

                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    backgroundColor = backgroundColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )
        }
    }}