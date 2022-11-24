package com.project.oplevapp.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CreateAccountScreen(
    navController: NavController,
    onCreateBtnClicked: () -> Unit = {},
    modifier: Modifier = Modifier
){
    var textFieldState by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = textFieldState,
            label = {
                Text(text = "Indtast email")
            },
            onValueChange = {
                textFieldState = it
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.6f),
            textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )


        OutlinedTextField(
            value = textFieldState,
            label = {
                Text(text = "Indtast adgangskode")
            },
            onValueChange = {
                textFieldState = it
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.6f),
            textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                modifier = Modifier.weight(1f),
                //Bruges ikke, i dette tilfælde
                /*
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                 */
                onClick = onCreateBtnClicked
            ) {
                Text("Opret profil")
            }
        }
    }
}

@Preview(showBackground = true)
@Preview
@Composable
fun CreateAccountPreview(){
    CreateAccountScreen(navController = rememberNavController())
}