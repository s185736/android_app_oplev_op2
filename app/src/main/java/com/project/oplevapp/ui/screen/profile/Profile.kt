/*Source: https://firebase.google.com/docs/auth/android/manage-users*/
package com.project.oplevapp.ui.screen.profile

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.R
import com.project.oplevapp.data.user.UserData
import com.project.oplevapp.data.user.UserRepository
import com.project.oplevapp.ui.shared.components.MyTextField
import com.project.oplevapp.ui.shared.components.PasswordVisibilityField
import com.project.oplevapp.ui.shared.components.UneditableTextField
import com.project.oplevapp.ui.theme.LightRed

//@Preview(showBackground = true)
@Composable
fun Profile(userData: UserData, navController: NavController, userRepository: UserRepository) {
    LazyColumn() {//modifier = Modifier.heightIn(100.dp, 48.dp)) {
        item {
            ProfileInfo(userData = userData, navController = navController, userRepository = userRepository)
        }
    }
}

@Composable
fun ProfileInfo(userData: UserData, navController: NavController, userRepository: UserRepository) {
    val content = LocalContext.current
    var db = Firebase.firestore.collection("users")

    var isLoading by remember {
        mutableStateOf(true)
    }
    var userId by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    var name by remember {
        mutableStateOf("")
    }
    var number by remember {
        mutableStateOf("")//userData.number)
    }
    var userProfiles  = remember {
        mutableStateListOf<UserData>()
    }

    val context = LocalContext.current

    val profileSaving = UserData(
        userID = userId,
        email = email,
        password = password,
        name = name,
        number = number
    )

    if (!isLoading) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        if (profileSaving.name != "" &&
                            profileSaving.password != "") {
                            userRepository.saveUser(profileSaving, content)
                        }
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = null,
                            tint = Color.White
                        )
                    },
                    backgroundColor = Color(0xFF053667)
                )
            },
            modifier = Modifier.padding()

        ) {
            Box {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(20.dp)
                                .clickable { navController.popBackStack() },
                        )
                        Text(
                            text = "Profil",
                            color = Color(5, 54, 103),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 40.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(start = 140.dp)
                        )

                        AlertDialogLogOut()
                    }

                    Spacer(
                        modifier = Modifier
                            .padding(bottom = 1.dp)
                    )
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
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 1.dp)
                        )
                        Text(
                            color = Color.Black,
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h4,
                            text = "Herunder kan du opdatere dine oplysninger.."
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 30.dp)
                        )

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
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 27.dp)
                        )

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

                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 27.dp)
                        )

                        UneditableTextField(
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
                            vectorPainter = painterResource(id = R.drawable.ic_outline_phone_24),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Locked"
                                )
                            }
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 27.dp)
                        )

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

                                val content =
                                    if (passwordVisible) "Skjul kodeord." else "Vis kodeord."
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = icon, content)
                                }
                            }
                        )
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 50.dp)
                        )
                        AlertDialogDeleteAccount()
                        Spacer(
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )

                    }
                }
            }
        }
    }
    else{
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator()

            try {
                db.addSnapshotListener{snapshot, e ->
                    if(snapshot != null){
                        for (document in snapshot){
                            println("WORKING")
                            Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                            val id = document.id
                            val name = document.data["name"] as String
                            val number = document.data["number"] as String
                            val email = document.data["email"] as String
                            val password = document.data["password"] as String
                            userId = id
                            userProfiles.add(
                                UserData(
                                    userID = id,
                                    email = email,
                                    password = password,
                                    name = name,
                                    number = number//.toInt()
                                )
                            )
                           }
                        isLoading = false

                    } else {
                        if (e != null) {
                            println(e.message)
                            Log.w(ContentValues.TAG, "Error getting documents.", e)
                        }
                    }
                }
            }catch (e: Exception){
                Log.w(ContentValues.TAG, "Error getting documents.", e)
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
fun AlertDialogLogOut() {
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
                    confirmButton = { Button( onClick = { openBox.value = false }) {
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
fun AlertDialogDeleteAccount() {
    MaterialTheme {
        Column {
            val openBox = remember { mutableStateOf(false)  }
            Button(onClick = {
                openBox.value = true}) {
                Text("Slet Bruger")
            }
            if (openBox.value) {
                AlertDialog(onDismissRequest = { openBox.value = false },
                    title = { Text(text = "Slet Bruger") },
                    text = { Text("Hovsa, du er ved at slette din bruger permanent. Er du sikker på det?") },
                    confirmButton = { Button( onClick = { openBox.value = false }) {
                            Text("Ja, slet")
                        }
                    },
                    dismissButton = { Button( onClick = { openBox.value = false }) {
                            Text("Nej, fortryd")
                        }
                    }
                )
            }
        }
    }
}
