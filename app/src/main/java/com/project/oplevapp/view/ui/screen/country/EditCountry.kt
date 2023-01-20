package com.project.oplevapp.view.ui.screen.country

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.oplevapp.model.data.Country
import com.project.oplevapp.model.repository.CountryRepository
import com.project.oplevapp.view.ui.nav.Screen


@Composable
fun EditCountry(country: Country, navController: NavController, countryRepository: CountryRepository){
    LazyColumn{
        item{
            ParallaxToolbar(country = country, navController)
            TextFieldsCountry(country = country, countryRepository = countryRepository , navController)
        }
    }
}

@Composable
fun TextFieldsCountry(country: Country, countryRepository: CountryRepository, navController: NavController){

    val context = LocalContext.current

    country.country = TextFieldWithIcons(country.country, "Land", Icons.Default.LocationOn)
    country.city = TextFieldWithIcons(textValue = country.city, label = "By", Icons.Default.LocationOn)
    country.departureDate = TextFieldWithIcons(textValue = country.departureDate, label = "Afrejse Dato", Icons.Default.DateRange)
    country.returnDate = TextFieldWithIcons(textValue = country.returnDate, label = "Hjemrejse Dato", Icons.Default.DateRange)
    country.info = TextFieldWithIcons(textValue = country.info, label = "Information", Icons.Default.Info)

    AddToShareBoardButton(title = "Opdater"){
        countryRepository.saveCountry(context = context, country = country)
        navController.popBackStack()
    }

    DeleteButton(title = "Slet") {
        if (country.id != null){
            countryRepository.deleteData(id = country.id, context = context)
            navController.navigate(Screen.TripList.route)
        }
    }
}

@Composable
fun TextFieldWithIcons(textValue: String, label: String, imageVector: ImageVector): String {
    var text by remember { mutableStateOf(TextFieldValue(textValue)) }
    OutlinedTextField(
        value = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 16.dp),
        leadingIcon = { Icon(imageVector = imageVector, contentDescription = "emailIcon") },
        //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onValueChange = {
            text = it
        },
        label = { Text(text = label) },
        placeholder = { Text(text = "Skriv tekst") },
    )

    return text.text
}

@Composable
fun DeleteButton(title: String, onClick: ()-> Unit) {
    Button(onClick =  onClick,
        elevation = null,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFD63B3B),
            contentColor = Color(0xFFFFFFFF)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 16.dp)
    ) {
        Text(text = title, Modifier.padding(12.dp), fontSize = 18.sp ,fontWeight = FontWeight.Medium)
    }
}