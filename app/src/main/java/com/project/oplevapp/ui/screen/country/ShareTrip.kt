package com.example.sharetrip

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.oplevapp.R


@Preview(showBackground = true)
@Composable
fun ShareTrip() {
    Scaffold {
        Box {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(27.dp)
            ) {
                Text(
                    text = "Del rejsen med andre",
                    color = Color(5, 54, 103),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                Text(
                    text = "Deltagerlisten",
                    color = Color(5, 54, 103),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left,
                )
                Spacer(modifier = Modifier.padding(bottom = 15.dp))

                var UserList by remember {
                    mutableStateOf("")
                }

                MyTextField(
                    text = UserList,
                    textSize = 15,
                    onValueChange = { UserList = it },
                    placeHolder = "Skriv deltager her ",
                    width = 320,
                    height = 300,
                    KeyboardType.Text,
                    visualTransformation = VisualTransformation.None,
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_baseline_supervised_user_circle_24)


                )
                Spacer(modifier = Modifier.padding(bottom = 27.dp))

                Text(
                    text = "Invitere andre",
                    color = Color(5, 54, 103),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.padding(bottom = 15.dp))

                var email by remember {
                    mutableStateOf("")
                }

                MyTextField(
                    text = email,
                    textSize = 15,
                    onValueChange = { email = it },
                    placeHolder = "Mail@mail.dk",
                    width = 320,
                    height = 57,
                    KeyboardType.Text,
                    visualTransformation = VisualTransformation.None,
                    Color.DarkGray,
                    Color.LightGray,
                    Color.Gray,
                    vectorPainter = painterResource(id = R.drawable.ic_outline_mail_outline_24),
                )

                Spacer(modifier = Modifier.padding(bottom = 5.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(5, 54, 103)),
                    shape = RoundedCornerShape(60),
                    modifier = Modifier.padding(70.dp),


                    onClick = { /** TO DO */ },

                    ) {
                    Text(
                        "Invitere nu",
                        color = Color.White,
                    )

                }


                @Composable
                fun InviteButton() {
                    Button(
                        onClick = {},
                        shape = RoundedCornerShape(60),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(5, 54, 103)),
                        modifier = Modifier
                            .height(38.dp)
                            .width(130.dp)
                    ) {
                        Row {
                            Text(
                                text = "Invitere nu",
                                color = Color.White,
                                fontSize = 12.sp,
                                modifier = Modifier.padding()
                            )

                        }

                    }
                }
            }
        }
    }
}


@Composable
fun MyTextField(
    text: String,
    textSize: Int,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    width: Int,
    height: Int,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    myTextColor: Color,
    backgroundColor: Color,
    placeHolderColor: Color,
    vectorPainter: Painter


) {

    Surface(
        modifier = Modifier.size(width.dp, height.dp),
        color = Color.White,
        shape = RectangleShape,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {


            TextField(
                value = text,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.copy(color = myTextColor),
                placeholder = {
                    Text(
                        text = placeHolder,
                        fontSize = textSize.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        textAlign = TextAlign.Left,
                        color = placeHolderColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = (height + 50).dp)
                    )

                },

                leadingIcon = { Icon(painter = vectorPainter, contentDescription = "") },


                visualTransformation = visualTransformation,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType
                ),

                modifier = Modifier.fillMaxSize(),

                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    backgroundColor = backgroundColor,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
            )
        }
    }
}
