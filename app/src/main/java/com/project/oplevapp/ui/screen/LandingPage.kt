package com.project.oplevapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.oplevapp.R



@Preview(showBackground = true)
@Composable
fun Onboardingcard() {
   Scaffold {
       Box {
           Image(
               painter= painterResource(id = R.drawable.blue1),
               contentDescription = "Background Image",
               modifier = Modifier.fillMaxSize(),
               contentScale = ContentScale.Crop
           )

           Column(
               Modifier
                   .padding(horizontal = 32.dp, vertical = 80.dp)
                   .fillMaxSize()
           ) {

               Spacer(Modifier.fillMaxHeight(0.6f))
               Card(
                   elevation = 4.dp,
                   modifier = Modifier
                       .border(
                           width = 1.dp,
                           color = Color.White.copy(0.1f),
                           shape = RoundedCornerShape(27.dp)
                       )
                       .clip(RoundedCornerShape(27.dp))
               ) {
                   Image(
                       painter = painterResource(id = R.drawable.blue1),
                       contentDescription = "Card background",
                       modifier = Modifier.fillMaxSize(),
                       contentScale = ContentScale.Crop
                   )
                   Column(
                       horizontalAlignment=Alignment.CenterHorizontally,
                       modifier = Modifier.padding(27.dp)
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
                           onClick = { /*TODO*/ },
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
                               "SE REJSER",
                               modifier = Modifier.padding(horizontal = 40.dp, vertical = 4.dp),
                               fontSize = 15.sp,
                               fontWeight = FontWeight.SemiBold
                           )
                       }

                   }

               }
           }
   }
    }


}

