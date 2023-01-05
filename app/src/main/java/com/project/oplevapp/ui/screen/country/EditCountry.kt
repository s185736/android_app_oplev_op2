package com.project.oplevapp.ui.screen.country

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.oplevapp.Screen
import com.project.oplevapp.data.Country
import com.project.oplevapp.data.Denmark


@Composable
fun EditCountry(country: Country, navController: NavController){
    LazyColumn{
        item{
            ParallaxToolbar(country = Denmark)
            TextFieldsCountry(country = Denmark, navController)
        }
    }
}

@Composable
fun TextFieldsCountry(country: Country, navController: NavController){
    TextFieldWithIcons(Denmark.country, "Land", Icons.Default.LocationOn)
    TextFieldWithIcons(textValue = Denmark.city, label = "By", Icons.Default.LocationOn)
    TextFieldWithIcons(textValue = Denmark.departureDate, label = "Afrejse Dato", Icons.Default.DateRange)
    TextFieldWithIcons(textValue = Denmark.returnDate, label = "Hjemrejse Dato", Icons.Default.DateRange)
    TextFieldWithIcons(textValue = Denmark.info, label = "Information", Icons.Default.Info)

    AddToShareBoardButton(title = "Redigere"){
        navController.navigate(Screen.Country.route)
    }
}

@Composable
fun TextFieldWithIcons(textValue: String, label: String, imageVector: ImageVector) {
    var text by remember { mutableStateOf(TextFieldValue(textValue)) }
    return OutlinedTextField(
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
}