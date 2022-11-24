package com.project.oplevapp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
fun ForgottenPasswordPage() {
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center ,
                    modifier = Modifier.padding(27.dp)
                ) {
                    Text(
                        text = "Glemt Adgangskode",
                        color = Color(5, 54, 103),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )


                    Spacer(modifier = Modifier.padding(bottom = 27.dp))


                    var email by remember { mutableStateOf("") }
                    MyTextField(
                        text = email,
                        textSize = 15,
                        onValueChange = { email = it.toString() },
                        placeHolder = "Email",
                        width = 320,
                        height = 57,
                        KeyboardType.Text,
                        visualTransformation = VisualTransformation.None,
                        Color.DarkGray,
                        Color.LightGray,
                        Color.Gray,
                        vectorPainter = painterResource(id = R.drawable.ic_outline_mail_outline_24)
                    )



                    Spacer(modifier = Modifier.padding(bottom = 27.dp))
                    ForgottenPassword()
                    TextButton(onClick = {/*TODO*/ }) {
                        Text(text = "Har du allerede en konto? Login", fontSize = 12.sp)
                    }


                }

            }
        }
    }
}

@Composable
fun ForgottenPassword() {
    Button(onClick = { /*TODO*/ },
        shape= RoundedCornerShape(60),
        colors = ButtonDefaults.buttonColors(backgroundColor= Color(5,54,103)),
        modifier = Modifier
            .height(38.dp)
            .width(230.dp)
    ) {
        Row {
            Text(text = "Glemt Adgangskode",
                color= Color.White,
                fontSize= 12.sp,
                modifier= Modifier.padding()
            )

        }

    }
}