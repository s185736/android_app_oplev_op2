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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.MainActivity
import com.project.oplevapp.R
import com.project.oplevapp.Screen
import com.project.oplevapp.ui.screen.country.MyTextField


@Preview
@Composable
fun LoginPage() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    Scaffold {
        Box {

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



                    Spacer(modifier = Modifier.padding(bottom = 27.dp))


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


                    Button(
                        modifier = Modifier.weight(1f),
                        //Bruges ikke, i dette tilfælde

                        onClick = {/**TO DO*/}

                    ) {
                        Text("Login")
                    }

                }
            }
        }
    }


=======
import com.project.oplevapp.ui.Screen
>>>>>>> 1f5b9870140b75e529400a0225b0705885f4ed7e

@Composable
fun LoginButton() {
    Button(
        onClick = { /**TO DO*/ },
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

