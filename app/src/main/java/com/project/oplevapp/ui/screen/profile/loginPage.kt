package com.project.oplevapp.ui.screen.profile

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.project.oplevapp.MainActivity
import com.project.oplevapp.R
import com.project.oplevapp.nav.Screen
// Guide https://www.youtube.com/watch?v=ZhDhUEFZDWU&ab_channel=Rotlin 

@Composable
fun LoginPage(
    navController: NavController,
    auth: FirebaseAuth
    ) {
    var email by remember {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    val isEmailValid by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordValid by derivedStateOf {
        password.length > 5
    }

    Scaffold {
        Box {
            Image(
                painter = painterResource(id = R.drawable.blue1),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White.copy(0.1f),
                        shape = RoundedCornerShape(27.dp)
                    )
                    .clip(RoundedCornerShape(50.dp))
                    .padding(top = 200.dp, start = 20.dp, end = 20.dp),
                backgroundColor = Color(red = 255, green = 255, blue = 255)
            ) {
                Column(
                    horizontalAlignment= Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center ,
                    modifier = Modifier.padding(27.dp)
                ) {
                    Text(
                        text = "Login",
                        color = Color(5,54,103),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.padding(bottom = 27.dp))

                    TextField(
                        value = email,
                        label = {
                            Text(text = "Indtast email")
                        },
                        onValueChange = {
                            email = it
                        },
                        //placeholder = { Text(text = "test@mail.dk")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {focusManager.moveFocus(FocusDirection.Down)}
                        ),
                        isError = isEmailValid
                    )
                    Spacer(modifier = Modifier.padding(bottom = 5.dp))

                    /*
                    MyTextField(
                        text = email,
                        textSize = 15,
                        onValueChange = {email=it},
                        placeHolder = "Email",
                        width = 320,
                        height = 57,
                        KeyboardType.Text,
                        visualTransformation = VisualTransformation.None,
                        Color.DarkGray ,
                        Color.LightGray ,
                        Color.Gray,
                        vectorPainter = painterResource(id = R.drawable.ic_outline_mail_outline_24),
                    )
                     */
                   TextField(
                        value = password,
                        label = {
                            Text(text = "Indtast adgangskode")
                        },
                        onValueChange = {
                            password = it
                        },
                        //placeholder = { Text(text = "mail123")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        textStyle = TextStyle(color = Color.LightGray, fontSize = 20.sp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,

                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {focusManager.clearFocus()}
                        ),
                        trailingIcon = {
                            val icon = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            val content = if (passwordVisible) "Skjul kodeord." else "Vis kodeord."
                            IconButton(onClick = {passwordVisible = !passwordVisible}){
                                Icon(imageVector  = icon, content)
                            }
                        },
                        isError = isPasswordValid
                    )

                    Spacer(modifier = Modifier.padding(bottom = 27.dp))

                    /*
                    MyTextField(
                        text = password,
                        textSize = 15,
                        onValueChange = {password=it},
                        placeHolder = "Adgangskode",
                        width = 320,
                        height = 57,
                        KeyboardType.Text,
                        visualTransformation = VisualTransformation.None,
                        Color.DarkGray ,
                        Color.LightGray ,
                        Color.Gray,
                        vectorPainter = painterResource(id = R.drawable.ic_outline_vpn_key_24)
                    )

                     */
                    Spacer(modifier = Modifier.padding(bottom = 27.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),Arrangement.SpaceBetween
                    ){
                        Row(verticalAlignment=Alignment.CenterVertically) {
                            Checkbox(checked=true, onCheckedChange={})
                            Text(text = "Gem adgangskode", fontSize=12.sp)

                        }
                        TextButton(onClick = {/*TODO*/}) {
                            Text(text = "Glemt adgangskode?", fontSize=12.sp)
                        }

                    }

                    Spacer(modifier = Modifier.padding(bottom = 27.dp))
                    //LoginButton( navController)

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(30.dp),
                        //Bruges ikke, i dette tilfælde

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
                        Text("Login")
                    }

                    TextButton(onClick = {navController.navigate(Screen.CreateAccount.route)}) {
                        Text(text = "Har du ikke en konto? Opret", fontSize = 12.sp)
                    }

                }
            }
        }
    }
}


@Composable
fun LoginButton(navController: NavController) {
    Button(
        onClick = { navController.navigate(Screen.TripList.route) },
    shape= RoundedCornerShape(60),
        colors = ButtonDefaults.buttonColors(backgroundColor=Color(5,54,103)),
        modifier = Modifier
            .height(38.dp)
            .width(130.dp)
        ) {
        Row {
            Text(text = "Login",
                color= Color.White,
                fontSize= 12.sp,
                modifier= Modifier.padding()
            )

        }

    }
}

/*
@Preview
@Composable
fun LoginPagePreview(){
    LoginPage(navController = rememberNavController())
}

 */

