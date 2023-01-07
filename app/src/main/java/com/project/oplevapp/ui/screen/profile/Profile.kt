package com.project.oplevapp.ui.screen.profile


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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

                    Text(
                        text = "Profil",
                        color = Color(5, 54, 103),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.padding(start = 200.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = {/**TODO**/},
                            modifier = Modifier.size(50.dp)) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_outline_logout_24),
                                contentDescription = "Slet Ide.",
                                tint = LightRed,
                            )
                        }
                    }


                }


                Spacer(modifier = Modifier.padding(bottom = 30.dp))


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
                    KeyboardType.Phone,
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
                    KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_vpn_key_24)
                )




                Spacer(modifier = Modifier.padding(bottom = 300.dp))


            Row {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(5, 54, 103)),
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
                Spacer(modifier = Modifier.padding(start = 200.dp))

                Row {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = LightRed),
                        shape = RoundedCornerShape(60),
                        modifier = Modifier
                            .height(45.dp)
                            .width(189.dp),
                        onClick = { /** TO DO */ },

                        ) {
                        Text(
                            "Fortryd",
                            color = Color.White,
                            fontSize = 16.sp
                        )


                    }
                }


            }
        }
    }
}