package com.project.oplevapp.ui.screen.profile


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.oplevapp.R
import com.project.oplevapp.ui.screen.country.MyTextField

@Preview(showBackground = true)
@Composable
fun CreateAccount() {
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


                var email by remember {
                    mutableStateOf("")
                }

                var password by remember {
                    mutableStateOf("")
                }
                var Name by remember {
                    mutableStateOf("")
                }
                var phone by remember {
                    mutableStateOf("")
                }

                var confirmPassword by remember {
                    mutableStateOf("")
                }

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

                MyTextField(
                    text = email,
                    textSize = 15,
                    onValueChange = { email = it },
                    placeHolder = "Email",
                    width = 320,
                    height = 57,
                    KeyboardType.Text,
                    visualTransformation = VisualTransformation.None,
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_mail_outline_24),
                )



                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                MyTextField(
                    text = phone,
                    textSize = 15,
                    onValueChange = { phone = it },
                    placeHolder = "Telefon",
                    width = 320,
                    height = 57,
                    KeyboardType.Text,
                    visualTransformation = VisualTransformation.None,
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_phone_24)
                )
                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                MyTextField(
                    text = password,
                    textSize = 15,
                    onValueChange = { password = it },
                    placeHolder = "Adgangskode",
                    width = 320,
                    height = 57,
                    KeyboardType.Text,
                    visualTransformation = VisualTransformation.None,
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_vpn_key_24)
                )

                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                MyTextField(
                    text = confirmPassword,
                    textSize = 15,
                    onValueChange = { confirmPassword = it },
                    placeHolder = " Bekræft adgangskode",
                    width = 320,
                    height = 57,
                    KeyboardType.Text,
                    visualTransformation = VisualTransformation.None,
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
                    Spacer(modifier = Modifier.padding(bottom = 27.dp))


                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(5,54,103)),
                        modifier = Modifier.padding(70.dp) ,


                        onClick = { /** TO DO */ },

                        ) {
                        Text("Opret")

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



























