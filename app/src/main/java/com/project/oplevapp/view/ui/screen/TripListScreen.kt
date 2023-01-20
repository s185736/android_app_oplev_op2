package com.project.oplevapp.view.ui.screen

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.R
import com.project.oplevapp.model.data.Country
import com.project.oplevapp.model.repository.CountryRepository
import com.project.oplevapp.view.ui.nav.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TripListScreen(
    navController: NavController,
    countryRepository: CountryRepository,
    onDestinationBtnClicked: () -> Unit = {}
){
    val context = LocalContext.current

    var countries  = remember {
        mutableStateListOf<Country>()
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    var db = Firebase.firestore.collection("countries")


    if (!isLoading){

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate(Screen.AddCountry.route) },
                content = {
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                    backgroundColor = Color(0xFF053667)
                )
            }
        ){
            CountryList(navController = navController, countries)
        }

        }
    else{
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator()


            try {
                db.addSnapshotListener{snapshot, e ->
                    if(snapshot != null){
                        for (document in snapshot){

                            Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                            val id = document.id
                            val countryFromDb = document.data["country"] as String
                            val city = document.data["city"] as String
                            val departureDate = document.data["departureDate"] as String
                            val returnDate = document.data["returnDate"] as String
                            val imageUrl = document.data["imageUrl"] as String
                            val info = document.data["info"] as String
                            countries.add(
                                Country(
                                    id = id,
                                    city = city,
                                    country = countryFromDb,
                                    departureDate = departureDate,
                                    returnDate = returnDate,
                                    imageUrl = imageUrl,
                                    info = info)
                            )

                        }
                        isLoading = false
                    } else {
                        if (e != null) {
                            println(e.message)
                            Log.w(ContentValues.TAG, "Error getting documents.", e)
                        }
                    }
                }
            }catch (e: Exception){
                Log.w(ContentValues.TAG, "Error getting documents.", e)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CountryList(navController: NavController, list: MutableList<Country>){

    var tipInput by remember { mutableStateOf("") }


    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Destination",
                fontSize = 26.sp, fontWeight = FontWeight.Bold
            )
        }
        SearchBar(
            label = R.string.Search,
            value = tipInput,
            onValueChanged = { tipInput = it }
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), contentPadding = PaddingValues(start = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ){
            items(list){ country ->
                    CountryCard(country = country, navController = navController)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { navController.navigate(Screen.AddCountry.route) }) {
                Text(text = "Tilføj rejse")
            }
            Button(onClick = { navController.navigate(Screen.Country.route) }) {
                Text(text = "København")
            }
        }
    }
}

@Composable
fun CountryCard(country: Country, navController: NavController){

    //pass data to nagvigation


Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp),
    shape = RoundedCornerShape(15.dp),
    elevation = 5.dp
) {
    Box(modifier = Modifier.height(250.dp)){
        Image(
            modifier = Modifier.fillMaxSize()
                .clickable {
                    //sending data
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "country",
                        value = country
                    )
                    navController.navigate(Screen.Country.route)
                           },
            painter = rememberAsyncImagePainter(model = country.imageUrl),
            contentDescription = " ",
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.TopStart
        ){
           Column {
               Text(
                   text = country.city,
                   style = TextStyle(
                       fontSize = 16.sp,
                       fontWeight = FontWeight.Bold
                   )
               )
               Text(
                   text = country.departureDate,
                   style = TextStyle(
                       fontSize = 12.sp,
                       fontWeight = FontWeight.Medium
                   )
               )
           }
        }
    }
}



}

@Composable
fun SearchBar(
    @StringRes label: Int,
    value: String,
    onValueChanged: (String) -> Unit,
    //modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        shape = RoundedCornerShape(15.dp),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "search") },
        onValueChange = onValueChanged,
        label = { Text(stringResource(label), fontWeight = FontWeight.Bold) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
        )
}