package com.project.oplevapp.ui.screen.profile

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.oplevapp.MainActivity


@Composable
fun CreateAccountScreen(
    auth: FirebaseAuth,
    navController: NavController,
    //onCreateBtnClicked: () -> Unit = {},
    modifier: Modifier = Modifier
){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val focusManager = LocalFocusManager.current

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.length > 5
    }


    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        OutlinedTextField(
            value = email,
            label = {
                Text(text = "Indtast email")
            },
            onValueChange = {
                email = it
            },
            placeholder = { Text(text = "test@test.dk")},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.6f),
            textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down)}
            ),
            isError = isEmailValid
        )

        OutlinedTextField(
            value = password,
            label = {
                Text(text = "Indtast adgangskode")
            },
            onValueChange = {
                password = it
            },
            placeholder = { Text(text = "test123")},
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.6f),
            textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,

                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {focusManager.clearFocus()}
            ),
            isError = isPasswordValid
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
                onClick = {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                Log.d(MainActivity::class.java.simpleName,"The user has succesfully logged in")
                            }else {
                                Log.d(MainActivity::class.java.simpleName, "The user has failed to log in", it.exception)
                            }
                        }
                },
                enabled = isEmailValid && isPasswordValid

            ) {
                Text("Opret profil")
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Preview
@Composable
fun CreateAccountPreview(){
    CreateAccountScreen(navController = rememberNavController(), auth = Firebase.auth)
}

 */