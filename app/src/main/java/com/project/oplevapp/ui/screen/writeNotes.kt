package com.project.oplevapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun writeNotes(navController: NavController) {

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
    BlackPreviousButton ({})
    Spacer(modifier = Modifier.padding(30.dp))
   Text(text = "Notesbog", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
}
        Text(text = "Min Personlige Noter", fontSize = 15.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    }



    Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(start = 0.dp, top = 25.dp),
        ) {
            // Write notes
            var noteWriting by remember { mutableStateOf("") }
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


