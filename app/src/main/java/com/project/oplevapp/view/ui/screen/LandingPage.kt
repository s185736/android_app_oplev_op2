package com.project.oplevapp.view.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.oplevapp.R
import com.project.oplevapp.view.ui.nav.Screen


@Composable
fun LandingPage(navController: NavController) {
    Scaffold {
        Box {
            Image(
                painter= painterResource(id = R.drawable.blue1),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Image(
                painter= painterResource(id = R.drawable.image1),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                Modifier
                    .padding(horizontal = 32.dp, vertical = 48.dp)
                    .fillMaxSize()
            ) {


                Spacer(Modifier.fillMaxHeight(0.14f))

                Column(
                    horizontalAlignment=Alignment.CenterHorizontally,
                    modifier = Modifier.padding(60.dp)
                ) {


                    Text(
                        "MED OPLEV KAN DU UDFORSKE VERDEN NEMMERE",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        "Velkommen til OPLEV, her kan du gemme og organisere alt inspiration, bookinger, ideer og hemmeligheder til alle de steder i verden du drømmer om at besøge",
                        color = Color.White.copy(0.8f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.padding(bottom = 27.dp))
                    Button(
                        onClick = { navController.navigate(Screen.Login.route) },
                        shape = RoundedCornerShape(percent = 50),
                        modifier = Modifier.border(
                            width = 1.dp,
                            color = Color.White.copy(0.5f),
                            shape = RoundedCornerShape(percent = 50)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(
                                151,
                                169,
                                246,
                                alpha = 0x32
                            ), contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Login side",
                            modifier = Modifier.padding(horizontal = 40.dp, vertical = 4.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.padding( 20.dp))

                    Image(
                        painter = painterResource(id = R.drawable.rejse),
                        contentDescription = "travel Image",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )

                }
            }
        }
    }
}






