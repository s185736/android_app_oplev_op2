package com.project.oplevapp.view.ui.screen.profile


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.R
import com.project.oplevapp.model.data.utils.ResultState
import com.project.oplevapp.model.data.user.User
import com.project.oplevapp.model.data.user.UserData
import com.project.oplevapp.model.repository.UserRepository
import com.project.oplevapp.viewmodel.UserViewModel
import com.project.oplevapp.model.data.utils.showMsg
import com.project.oplevapp.view.ui.nav.Screen
import com.project.oplevapp.view.ui.shared.components.MyTextField
import com.project.oplevapp.view.ui.shared.components.PasswordVisibilityField
import com.project.oplevapp.view.ui.shared.ProgressIndicator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//Der kan tastes mail og adgangskode, hvor den så opretter til firebase.

@Composable
fun CreateAccount(viewModel: UserViewModel = hiltViewModel(), navController: NavController, userRepository: UserRepository) {
    LazyColumn {
        item {
            CreateProgress(navController = navController, userRepository = userRepository)        }
    }
}

@Composable
fun CreateProgress(
    navController: NavController, viewModel: UserViewModel = hiltViewModel(),
    userRepository: UserRepository
) {
    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var check by remember { mutableStateOf(false) }
    var openDialog by remember { mutableStateOf(false) }
    var identicalPassword by remember { mutableStateOf(true) }
    var isDialog by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    if (isDialog)
        ProgressIndicator()

    Scaffold {
        Box {

            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.popBackStack() },
            )
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
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = icon, content)
                        }
                    }
                )

                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                PasswordVisibilityField(
                    text = confirmPassword,
                    textSize = 15,
                    onValueChange = { confirmPassword = it },
                    placeHolder = " Bekræft adgangskode",
                    width = 320,
                    height = 57,
                    KeyboardType.Password,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_lock_24),
                    trailingIcon = {
                        val icon = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val content = if (passwordVisible) "Skjul kodeord." else "Vis kodeord."
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = icon, content)
                        }
                    }
                )

                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = check, onCheckedChange = { check = it })
                        TextButton(
                            onClick = { openDialog = true }) {
                            Text(
                                text = "Bekræfter vilkår, betingelser og GDPR regler",
                                fontSize = 12.sp
                            )
                        }
                        if (openDialog) {
                            AlertDialog(
                                onDismissRequest = {
                                    openDialog = false
                                },
                                title = {
                                    Text(text = "Vilkår, betingelser og GDPR")
                                },
                                text = {
                                    Text(
                                        text = "Jeg giver OPLEV APS samtykke til at opbevare mine\n" +
                                                "kontaktinformationer. Informationer må bruges til forbedring af appen\n" +
                                                "og markedsføring."
                                    )
                                },
                                confirmButton = {
                                    Button(

                                        onClick = {
                                            openDialog = false
                                        }) {
                                        Text("Luk besked")
                                    }
                                },
                            )
                        }
                    }
                }
                //Tjekker hvis password er det samme som confirmpassword
                //skal få lavet så¨man få besked på hvis koden ikke er identisk
                identicalPassword = password == confirmPassword

                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(5, 54, 103)),
                    //modifier = Modifier.padding(27.dp),
                    onClick = {
                        scope.launch(Dispatchers.Main) {
                            viewModel.userCreate(
                                User(
                                    email,
                                    password,
                                )
                            ).collect {
                                var temp = false
                                isDialog = when (it) {
                                    is ResultState.Success -> {
                                        context.showMsg(it.data)
                                        temp = true
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
                                if (temp) {
                                    val userData = UserData(
                                        userID = Firebase.auth.currentUser?.uid.toString(),
                                        email = email,
                                        password = password,
                                        name = name,
                                        number = number//.toInt()
                                    )
                                    userRepository.saveUser(userData = userData, context = context)
                                    navController.navigate(Screen.Login.route)
                                    temp = false
                                }
                            }
                        }
                    }, enabled = check && identicalPassword

                    ) {
                    Text("Opret")
                }

                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                TextButton(onClick = { navController.navigate(Screen.Login.route) }) {
                    Text(text = "Har du allerede en konto? Login", fontSize = 12.sp)
                }
            }
        }
    }
}






























