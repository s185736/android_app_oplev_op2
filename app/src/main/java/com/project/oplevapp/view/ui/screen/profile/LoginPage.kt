package com.project.oplevapp.view.ui.screen.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.R
import com.project.oplevapp.model.data.utils.ResultState
import com.project.oplevapp.model.data.user.User
import com.project.oplevapp.model.repository.UserRepository
import com.project.oplevapp.viewmodel.UserViewModel

import com.project.oplevapp.model.data.utils.showMsg
import com.project.oplevapp.view.ui.nav.Screen
import com.project.oplevapp.view.ui.shared.ProgressIndicator
import com.project.oplevapp.view.ui.shared.components.MyTextField
import com.project.oplevapp.view.ui.shared.components.PasswordVisibilityField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Guide https://www.youtube.com/watch?v=ZhDhUEFZDWU&ab_channel=Rotlin

@Composable
fun LoginPage(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
    ) {
    val uid = Firebase.auth.currentUser?.uid.toString()
    val user = Firebase.auth.currentUser
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var email by remember { mutableStateOf("dtu@dtu.dk") }
    var password by rememberSaveable { mutableStateOf("dtu123") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var isDialog by remember { mutableStateOf(false) }


    if (isDialog)
        ProgressIndicator()


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
                    .padding(top = 50.dp, start = 20.dp, end = 20.dp),
                backgroundColor = Color(red = 255, green = 255, blue = 255)
            ) {
                Column(
                    horizontalAlignment= Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center ,
                    modifier = Modifier.padding(27.dp)
                ) {
                    Text(
                        text = "Log Ind",
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
                        KeyboardType.Email,
                        visualTransformation = VisualTransformation.None,
                        Color.DarkGray ,
                        Color.LightGray ,
                        Color.Gray,
                        vectorPainter = painterResource(id = R.drawable.ic_outline_mail_outline_24),
                    )

                    Spacer(modifier = Modifier.padding(bottom = 27.dp))

                    PasswordVisibilityField(
                        text = password,
                        textSize = 15,
                        onValueChange = { password = it },
                        placeHolder = "Adgangskode",
                        width = 320,
                        height = 57,
                        KeyboardType.Password,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        Color.DarkGray,
                        Color.LightGray,
                        Color.Gray,
                        vectorPainter = painterResource(id = R.drawable.ic_outline_vpn_key_24),
                        trailingIcon = {
                            val icon = if (passwordVisible)
                                Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff

                            val content = if (passwordVisible) "Skjul kodeord." else "Vis kodeord."
                            IconButton(onClick = {passwordVisible = !passwordVisible}){
                                Icon(imageVector  = icon, content)
                            }
                        }
                    )


                    Spacer(modifier = Modifier.padding(bottom = 27.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),Arrangement.SpaceBetween
                    ){
                        Row(verticalAlignment=Alignment.CenterVertically) {
                            Checkbox(checked=true, onCheckedChange={})
                            Text(text = "Gem adgangskode", fontSize=12.sp)

                        }
                        TextButton(onClick = { navController.navigate(Screen.ResetPassword.route)}) {
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
                                            user?.uid
                                            navController.navigate(Screen.TripList.route)
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
                        Text("Log ind")
                    }
                    //Dette skaffer UID fra den nuværende bruger.
                    Log.i("main", uid)

                    TextButton(onClick = {navController.navigate(Screen.CreateAccount.route)}) {
                        Text(text = "Har du ikke en konto? Opret", fontSize = 12.sp)
                    }

                }
            }
        }
    }
}

@Composable
fun LoginPreview(){

}


