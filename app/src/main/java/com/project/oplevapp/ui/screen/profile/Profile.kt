package com.project.oplevapp.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.oplevapp.R
import com.project.oplevapp.ui.shared.components.BlackPreviousButton
import com.project.oplevapp.ui.shared.components.MyTextField
import com.project.oplevapp.ui.shared.components.UneditableTextField
import com.project.oplevapp.ui.theme.LightRed

@Preview(showBackground = true)
@Composable
fun Profile() {
    Scaffold {
        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(27.dp)
            ) {
                Row {
                    BlackPreviousButton({})
                    Text(
                        text = "Profil",
                        color = Color(5, 54, 103),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 35.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(start = 140.dp))

                    AlertDialogLogOut()
                }
                Spacer(modifier = Modifier.padding(bottom = 1.dp))

                ShowProfileImage()

                var email by remember {
                    mutableStateOf("Hans@mail.com")
                }
                var password by remember {
                    mutableStateOf("123456")
                }
                var Name by remember {
                    mutableStateOf("Hans Ole")
                }
                var phone by remember {
                    mutableStateOf("42424242")
                }

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
                        text = Name
                    )
                    Spacer(modifier = Modifier.padding(bottom = 1.dp))

                    MyTextField(
                        text = Name,
                        textSize = 15,
                        onValueChange = { Name = it },
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
                    )



                    Spacer(modifier = Modifier.padding(bottom = 27.dp))

                    UneditableTextField(
                        text = phone,
                        textSize = 15,
                        onValueChange = { phone = it },
                        placeHolder = "Telefon",
                        width = 320,
                        height = 57,
                        KeyboardType.Phone,
                        visualTransformation = VisualTransformation.None,
                        myTextColor = Color.DarkGray,
                        backgroundColor = Color.LightGray,
                        placeHolderColor = Color.Gray,
                        vectorPainter = painterResource(id = R.drawable.ic_outline_phone_24),
                    )
                    Spacer(modifier = Modifier.padding(bottom = 27.dp))

                    MyTextField(
                        text = password,
                        textSize = 15,
                        onValueChange = { password = it },
                        placeHolder = "Adgangskode",
                        width = 320,
                        height = 57,
                        KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        Color.DarkGray,
                        Color.LightGray,
                        Color.Gray,
                        vectorPainter = painterResource(id = R.drawable.ic_outline_vpn_key_24)
                    )
                    Spacer(modifier = Modifier.padding(bottom = 100.dp))
                    AlertDialogSletBruger()
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
                                .width(189.dp),
                            onClick = { /** TO DO */ },

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
fun AlertDialogSletBruger() {
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