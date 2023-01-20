package com.project.oplevapp.view.ui.screen.country



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.oplevapp.R
import com.project.oplevapp.model.data.Country
import com.project.oplevapp.model.repository.CountryRepository
import com.project.oplevapp.view.ui.nav.Screen
import com.project.oplevapp.view.ui.shared.components.WhitePreviousButton


@Composable
fun AddTrip(navController: NavController, countryRepository: CountryRepository) {
    Scaffold {

        Box {
            Image(
                painter = painterResource(id = R.drawable.blue1),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 10.dp)
            ) {
                WhitePreviousButton{navController.popBackStack()}
                CountryInfo(navController, countryRepository)
            }
        }
    }
}



    @Composable
   fun CountryInfo(navController: NavController, countryRepository: CountryRepository){

        val content = LocalContext.current

       Card(
           modifier = Modifier
               .padding(start = 0.dp, top = 50.dp)
               .fillMaxWidth(),
           elevation = 10.dp,
           shape = RoundedCornerShape(20.dp),
           backgroundColor = Color(red = 255, green = 255, blue = 255)

       )

       {
           Column(
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center,
               modifier = Modifier.padding(start = 0.dp,top = 25.dp),

           ) {


               Text(
                   text = "Tilføj Rejse",
                   fontFamily = FontFamily.Default,
                   fontWeight = FontWeight.ExtraBold,
                   fontSize = 25.sp,
                   color = Color(red = 5, green = 54, blue = 103),
                   modifier = Modifier
                       .padding(top = 30.dp, start = 0.dp)
               )
                Spacer(modifier = Modifier.size(20.dp))
               //CountryName

               var country by remember { mutableStateOf("") }

               MyTextField(
                   text = country,
                   textSize = 15,
                   onValueChange = { country = it },
                   placeHolder = "Land",
                   width = 300,
                   height = 51,
                   KeyboardType.Text,
                   visualTransformation = VisualTransformation.None,
                   Color.DarkGray,
                   Color.LightGray,
                   Color.Gray,
                   vectorPainter = painterResource(id = R.drawable.ic_baseline_near_me_24)
               )


               Spacer(modifier = Modifier.size(20.dp))

               //CityName
               var city by remember { mutableStateOf("") }
               MyTextField(
                   text = city,
                   textSize = 15,
                   onValueChange = { city = it },
                   placeHolder = "By",
                   width = 300,
                   height = 51,
                   KeyboardType.Text,
                   visualTransformation = VisualTransformation.None,
                   Color.DarkGray,
                   Color.LightGray,
                   Color.Gray,
                   vectorPainter = painterResource(id = R.drawable.ic_outline_place_24)
               )
               Spacer(modifier = Modifier.size(20.dp))

               //DateDepature
               var dateDeparture by remember { mutableStateOf("") }
               MyTextField(
                   text = dateDeparture,
                   textSize = 15,
                   onValueChange = { dateDeparture = it },
                   placeHolder = "Afrejse dato",
                   width = 300,
                   height = 51,
                   KeyboardType.Text,
                   visualTransformation = VisualTransformation.None,
                   Color.DarkGray,
                   Color.LightGray,
                   Color.Gray,
                   vectorPainter = painterResource(id = R.drawable.ic_baseline_schedule_24)
               )

               Spacer(modifier = Modifier.size(20.dp))

               //DateArrival
               var dateArrival by remember { mutableStateOf("") }
               MyTextField(
                   text = dateArrival,
                   textSize = 15,
                   onValueChange = { dateArrival = it },
                   placeHolder = "Hjemrejse dato",
                   width = 300,
                   height = 51,
                   KeyboardType.Text,
                   visualTransformation = VisualTransformation.None,
                   Color.DarkGray,
                   Color.LightGray,
                   Color.Gray,
                   vectorPainter = painterResource(id = R.drawable.ic_baseline_schedule_24)

               )
               Spacer(modifier = Modifier.size(15.dp))

               // ImageUrl
               var imageUrl by remember { mutableStateOf("") }
               MyTextField(
                   text = imageUrl,
                   textSize = 15,
                   onValueChange = { imageUrl = it },
                   placeHolder = "Image Url",
                   width = 300,
                   height = 51,
                   KeyboardType.Text,
                   visualTransformation = VisualTransformation.None,
                   Color.DarkGray,
                   Color.LightGray,
                   Color.Gray,
                   vectorPainter = painterResource(id = R.drawable.ic_baseline_schedule_24)

               )
               Spacer(modifier = Modifier.size(15.dp))

               //Information about trip
               var info by remember { mutableStateOf("") }
               MyTextField(
                   text = info,
                   textSize = 15,
                   onValueChange = { info = it },
                   placeHolder = "info",
                   width = 300,
                   height = 51,
                   KeyboardType.Text,
                   visualTransformation = VisualTransformation.None,
                   Color.DarkGray,
                   Color.LightGray,
                   Color.Gray,
                   vectorPainter = painterResource(id = R.drawable.ic_baseline_schedule_24)

               )
               Spacer(modifier = Modifier.size(15.dp))

                val countryToSave = Country(
                    id = null,
                    country = country,
                    city = city,
                    departureDate = dateDeparture,
                    returnDate = dateArrival,
                    imageUrl = imageUrl,
                    info = info
                )

               AddToShareBoardButton(title = "Tilføj"){
                   if (countryToSave.country != "" && countryToSave.city != "" && countryToSave.imageUrl != ""){
                       countryRepository.saveCountry(countryToSave, content)
                       navController.navigate(Screen.TripList.route)
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
            )
        }
    }}

