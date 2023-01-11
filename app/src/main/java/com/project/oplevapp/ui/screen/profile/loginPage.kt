package com.project.oplevapp.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.oplevapp.R
import com.project.oplevapp.data.user.ResultState
import com.project.oplevapp.data.user.User
import com.project.oplevapp.data.user.ui.AuthViewModel

import com.project.oplevapp.data.user.utils.showMsg
import com.project.oplevapp.nav.Screen
import com.project.oplevapp.ui.screen.country.MyTextField
import com.project.oplevapp.ui.shared.components.ProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Guide https://www.youtube.com/watch?v=ZhDhUEFZDWU&ab_channel=Rotlin

@Composable
fun LoginPage(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
    ) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var isDialog by remember { mutableStateOf(false) }
    if (isDialog)
        ProgressIndicator()

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

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
                    //LoginButton( navController)

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp),


                        onClick = {
                            scope.launch(Dispatchers.Main){
                                viewModel.userLogin(
                                    User(
                                        email,
                                        password
                                    )
                                ).collect{
                                    isDialog = when(it){
                                        is ResultState.Success -> {
                                            context.showMsg(it.data)
                                            false
                                        }
                                        is ResultState.Failure->{
                                            context.showMsg(it.msg.toString())
                                            false
                                        }
                                        ResultState.Loading->{
                                            true
                                        }
                                    }
                                }
                            }

                        },
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

