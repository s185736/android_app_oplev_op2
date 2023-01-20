/*Source: https://firebase.google.com/docs/auth/android/manage-users*/
package com.project.oplevapp.view.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.R
import com.project.oplevapp.model.data.user.UserData
import com.project.oplevapp.model.repository.UserRepository
import com.project.oplevapp.view.ui.shared.components.MyTextField
import com.project.oplevapp.view.ui.shared.components.PasswordVisibilityField
import com.project.oplevapp.view.ui.shared.components.UneditableTextField
import com.project.oplevapp.view.ui.theme.LightRed


@Composable
fun Profile( navController: NavController, userRepository: UserRepository) {
    LazyColumn {//modifier = Modifier.heightIn(100.dp, 48.dp)) {
        item {
            ProfileInfo(navController = navController, userRepository = userRepository)
        }
    }
}

@Composable
fun ProfileInfo(navController: NavController, userRepository: UserRepository) {
    val context = LocalContext.current
    val userID = Firebase.auth.currentUser?.uid.toString()

    var email by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    userRepository.getUser(
        userID = userID,
        context = context
    ){
            data ->
        name = data.name
        email = data.email
        number = data.number
        password = data.password
    }

    Scaffold {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(27.dp)
            ) {
                Row {
                    Text(
                        text = "Profil",
                        color = Color(5, 54, 103),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 35.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(start = 140.dp))

                    AlertDialogLogOut(navController, userRepository)
                }

                Spacer(modifier = Modifier.padding(bottom = 1.dp))
                ShowProfileImage()

                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        color = Color.Blue,
                        fontSize = 24.sp,
                        style = MaterialTheme.typography.h4,
                        text = name
                    )
                    Spacer(modifier = Modifier.padding(bottom = 1.dp))
                    Text(
                        color = Color.Black,
                        fontSize = 16.sp,
                        style = MaterialTheme.typography.h4,
                        text = "Herunder kan du opdatere dine oplysninger.."
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

                    UneditableTextField(
                        text = email,
                        textSize = 15,
                        onValueChange = { email = it },
                        placeHolder = "Email",
                        width = 320,
                        height = 57,
                        KeyboardType.Email,
                        visualTransformation = VisualTransformation.None,
                        myTextColor = Color.DarkGray,
                        backgroundColor = Color.LightGray,
                        placeHolderColor = Color.Gray,
                        vectorPainter = painterResource(id = R.drawable.ic_outline_mail_outline_24),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Locked"
                            )
                        }
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
                        myTextColor = Color.DarkGray,
                        backgroundColor = Color.LightGray,
                        placeHolderColor = Color.Gray,
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

                    Spacer(modifier = Modifier.padding(bottom = 50.dp))
                    AlertDialogDeleteAccount(navController, userRepository)
                    Spacer(modifier = Modifier.padding(bottom = 10.dp))

                    Row {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color(
                                        5,
                                        54,
                                        103
                                    )
                                ),
                                shape = RoundedCornerShape(60),
                                modifier = Modifier
                                    .height(45.dp)
                                    .width(189.dp)
                                    .testTag("updateButton"),
                                onClick = {
                                    val userData = UserData(
                                        userID = Firebase.auth.currentUser?.uid.toString(),
                                        email = email,
                                        password = password,
                                        name = name,
                                        number = number//.toInt()
                                    )
                                    val user = Firebase.auth.currentUser
                                    user!!.updatePassword(password).addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                userRepository.updateUser(userData = userData, context = context)
                                            }
                                        }
                                },

                                ) {
                                Text(
                                    "Opdater",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
private fun ShowProfileImage(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(100.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.transparent_logo),
            contentDescription = "profil billede.",
            modifier = modifier.size(100.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun AlertDialogLogOut(navController: NavController, userRepository: UserRepository) {
    val context = LocalContext.current
    MaterialTheme {
        Row(modifier = Modifier.fillMaxWidth()) {
            val openBox = remember { mutableStateOf(false)  }
            IconButton(
                onClick = {openBox.value = true},
                modifier = Modifier.size(67.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_logout_24),
                    contentDescription = "Log Ud.",
                    tint = LightRed,
                )
            }
            if (openBox.value) {
                AlertDialog(onDismissRequest = { openBox.value = false },
                    title = { Text(text = "Log Ud") },
                    text = { Text("Du er ved, at logge dig selv ud af appen. Er du sikker på det?") },
                    confirmButton = { Button(
                        onClick = {
                            openBox.value = false
                            userRepository.signUserOut(navController = navController, context = context)
                        }) {
                        Text("Ja")
                    }
                    },
                    dismissButton = { Button( onClick = { openBox.value = false }) {
                        Text("Nej")
                    }
                    }
                )
            }
        }
    }
}
@Composable
fun AlertDialogDeleteAccount(navController: NavController, userRepository: UserRepository) {
    val context = LocalContext.current
    MaterialTheme {
        Column {
            val openBox = remember { mutableStateOf(false) }
            Button(onClick = {
                openBox.value = true
            }
            ) {
                Text("Slet Bruger")
            }
            if (openBox.value) {
                AlertDialog(onDismissRequest = { openBox.value = false },
                    title = { Text(text = "Slet Bruger") },
                    text = { Text("Hovsa, du er ved at slette din bruger permanent. Er du sikker på det?") },
                    confirmButton = {
                        Button(
                            onClick = {
                                openBox.value = false
                                userRepository.deleteUser(
                                    navController = navController,
                                    context = context
                                )
                            }) {
                            Text("Ja, slet")
                        }
                    },
                    dismissButton = {
                        Button(onClick = { openBox.value = false }) {
                            Text("Nej, fortryd")
                        }
                    }
                )
            }
        }
    }
}

