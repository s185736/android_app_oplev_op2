package com.project.oplevapp.view.ui.screen.country

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.project.oplevapp.model.data.Country
import com.project.oplevapp.view.ui.nav.Screen

val AppBarExpendedHeight = 400.dp

@Composable
fun Shareboard(country: Country, navController: NavController) {
    LazyColumn{
        item {
            ParallaxToolbar(country, navController)
            BasisInfo(country, navController)
            Description(country, navController)
            AddToShareBoardButton("Idea Portal"){
                navController.navigate(Screen.IdeaScreen.route)
            }
        }
    }
}

@Composable
fun ParallaxToolbar(country: Country, navController: NavController) {
    val imageHeight = AppBarExpendedHeight
    TopAppBar(
        contentPadding = PaddingValues(),
        backgroundColor = Color.White,
        modifier = Modifier.height(AppBarExpendedHeight)
    ) {
        Column{
            Box(
                Modifier.height(imageHeight)
            ){
                Image(
                    painter = rememberAsyncImagePainter(model = country.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.padding(20.dp).clickable { navController.popBackStack() },
                )
            }
        }
    }
}

@Composable
fun BasisInfo(country: Country, navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)
            .padding(horizontal = 25.dp)
    ) {
        Text(text = country.city, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Button(
            onClick = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    key = "country",
                    value = country
                )
                navController.navigate(Screen.EditCountry.route)
            },
            elevation = null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFFFFFF),
                contentColor = Color(0xFF000000)
            )) {
            Icon(imageVector = Icons.Default.Edit,
                contentDescription = null
            )
        }
    }
    Divider(thickness = 2.dp)
    OptionBar(country)
    Divider(thickness = 2.dp)
}

@Composable
fun OptionBar(country: Country){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        InfoColumn(Icons.Default.Place, "6 hours")
        InfoColumn(Icons.Default.DateRange, country.departureDate)
    }
}

@Composable
fun Description(country: Country, navController: NavController){
    Box(modifier = Modifier.height(16.dp)) {
    }
    TitleStandard(text = "Info:")
    TextStandard(text = country.info)
    TitleStandard(text = "Afrejse:")
    TextStandard(text = country.departureDate)
    TitleStandard(text = "Hjemrejse:")
    TextStandard(text = country.returnDate)
    TitleStandard(text = "Land:")
    TextStandard(text = country.country)
    TitleStandard(text = "By:")
    TextStandard(text = country.city)
    TitleStandard(text = "Tilføj deltagere:")
    Button(
        onClick = {
                  navController.navigate(Screen.TripShare.route)
        },
        elevation = null,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFFFFFF),
            contentColor = Color(0xFF000000)
        )) {
        Icon(imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Composable
fun AddToShareBoardButton(title: String, onClick: ()-> Unit) {
    Button(onClick =  onClick,
        elevation = null,
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF053667),
            contentColor = Color(0xFFFFFFFF)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp, vertical = 16.dp)
    ) {
        Text(text = title, Modifier.padding(12.dp), fontSize = 18.sp ,fontWeight = FontWeight.Medium)
    }
}

@Composable
fun TitleStandard(text: String){
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(top = 10.dp)
            .padding(horizontal = 25.dp)
    )
}

@Composable
fun TextStandard(text: String){
    Text(
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        modifier = Modifier.padding(horizontal = 25.dp, vertical = 8.dp)
    )
}

@Composable
fun InfoColumn(iconResource: ImageVector, text: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = iconResource,
            contentDescription = null,
            modifier = Modifier.height(24.dp)
        )
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}