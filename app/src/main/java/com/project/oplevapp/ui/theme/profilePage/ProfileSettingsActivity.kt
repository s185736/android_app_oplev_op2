package com.project.oplevapp.ui.theme.profilePage;
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.readable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.oplevapp.R
import com.project.oplevapp.ui.shared.components.MyTextField

@Preview(showBackground = true)
@Composable
fun ProfileSettingsActivity() {
        Scaffold {
                Box {

                        Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(27.dp)
                        ) {
                                Text(
                                        text = "Profil",
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

                                ProfileTextField(
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

                                Column(
                                        // we are using column to align our
                                        // imageview to center of the screen.
                                        modifier = Modifier.fillMaxWidth().fillMaxHeight(),

                                        // below line is used for specifying
                                        // vertical arrangement.
                                        verticalArrangement = Arrangement.Center,

                                        // below line is used for specifying
                                        // horizontal arrangement.
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                        // below line is use to get
                                        // the context for our app.
                                        val context = LocalContext.current

                                        // below line is use to create a button.
                                        Button(
                                                // below line is use to add onclick
                                                // parameter for our button onclick
                                                onClick = {
                                                        // when user is clicking the button
                                                        // we are displaying a toast message.
                                                        Toast.makeText(context, "Welcome to Geeks for Geeks", Toast.LENGTH_LONG)
                                                                .show()
                                                },
                                                // in below line we are using modifier
                                                // which is use to add padding to our button
                                                modifier = Modifier.padding(all = Dp(10F)),

                                                // below line is use to set or
                                                // button as enable or disable.
                                                enabled = true,

                                                // below line is use to
                                                // add border to our button.
                                                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),

                                                // below line is use to add shape for our button.
                                                shape = MaterialTheme.shapes.medium,
                                        )
                                        // below line is use to
                                        // add text on our button
                                        {
                                                Text(text = "Geeks for Geeks", color = Color.White)
                                        }
                                }
                        }
                }
        }
}

@Composable
fun ProfileTextField(
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
                shape = RoundedCornerShape(35),
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
                                leadingIcon = {Icon(painter = vectorPainter, contentDescription ="" ) },
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
                                readOnly = true
                        )
                }
        }
}