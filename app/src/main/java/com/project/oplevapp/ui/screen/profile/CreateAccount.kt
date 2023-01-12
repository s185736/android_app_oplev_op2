package com.project.oplevapp.ui.screen.profile


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.R
import com.project.oplevapp.data.user.utils.ResultState
import com.project.oplevapp.data.user.User
import com.project.oplevapp.data.user.UserData
import com.project.oplevapp.data.user.UserRepository
import com.project.oplevapp.data.user.ui.UserViewModel
import com.project.oplevapp.data.user.utils.showMsg
import com.project.oplevapp.ui.shared.components.MyTextField
import com.project.oplevapp.ui.shared.components.PasswordVisibilityField
import com.project.oplevapp.ui.shared.components.ProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//Der kan tastes mail og adgangskode, hvor den så opretter til firebase.

@Composable
fun CreateAccount(
    viewModel: UserViewModel = hiltViewModel(),
    userRepository: UserRepository
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var userID by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var isDialog by remember { mutableStateOf(false) }
    if (isDialog)
        ProgressIndicator()

    Scaffold {
        Box {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(27.dp)
            ) {
                Text(
                    text = "Opret Konto",
                    color = Color(5, 54, 103),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(bottom = 30.dp))


                var passwordVisible by rememberSaveable {
                    mutableStateOf(false)
                }

                var confirmPassword by remember {
                    mutableStateOf("")
                }

                MyTextField(
                    text = name,
                    textSize = 15,
                    onValueChange = { name = it },
                    placeHolder = "Navn",
                    width = 320,
                    height = 57,
                    KeyboardType.Text,
                    visualTransformation = VisualTransformation.None,
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_person_24),
                )
                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                MyTextField(
                    text = email,
                    textSize = 15,
                    onValueChange = { email = it },
                    placeHolder = "Email",
                    width = 320,
                    height = 57,
                    KeyboardType.Email,
                    visualTransformation = VisualTransformation.None,
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_mail_outline_24),
                )

                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                MyTextField(
                    text = number,
                    textSize = 15,
                    onValueChange = { number = it },
                    placeHolder = "Telefon",
                    width = 320,
                    height = 57,
                    KeyboardType.Phone,
                    visualTransformation = VisualTransformation.None,
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_phone_24)
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

                MyTextField(
                    text = confirmPassword,
                    textSize = 15,
                    onValueChange = { confirmPassword = it },
                    placeHolder = " Bekræft adgangskode",
                    width = 320,
                    height = 57,
                    KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_lock_24)
                )


                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = true, onCheckedChange = {})
                        Text(text = "Agree with terms and conditions", fontSize = 12.sp)

                    }
                }
                    //Spacer(modifier = Modifier.padding(bottom = 27.dp))


                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(5,54,103)),
                        modifier = Modifier.padding(70.dp) ,
                        onClick = {
                            scope.launch(Dispatchers.Main) {
                                viewModel.userCreate(
                                      User(
                                          email,
                                          password,

                                      )
                                  ).collect {
                                      isDialog = when (it) {
                                          is ResultState.Success -> {
                                              context.showMsg(it.data)
                                              false
                                          }
                                          is ResultState.Failure -> {
                                              context.showMsg(it.msg.toString())
                                              false
                                          }
                                          ResultState.Loading -> {
                                              true
                                          }
                                      }
                                  }
                              }
                                  },

                        ) {
                        Text("Opret")
                    }

                //se om det kan sætte i en knap måske, hvor den gemmer først når brugeren er oprettet
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(5,54,103)),
                    onClick = {
                    val userData = UserData(
                        userID = Firebase.auth.currentUser?.uid.toString(),
                        email = email,
                        password = password ,
                        name = name,
                        number = number.toInt()
                    )
                    userRepository.saveUser(userData = userData, context = context)
                }) {
                    Text(text = "Gem profil i database")
                }

                    TextButton(onClick = {}) {

                        Text(text = "Har du allerede en konto? Login", fontSize = 12.sp)

                    }
            }
        }
    }


    @Composable
    fun OpretButton() {
        Button(
            onClick = {},
            shape = RoundedCornerShape(60),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(5,54,103)),
            modifier = Modifier
                .height(38.dp)
                .width(130.dp)
        ) {
            Row {
                Text(
                    text = "Opret",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding()
                )

            }

        }
    }
}



























