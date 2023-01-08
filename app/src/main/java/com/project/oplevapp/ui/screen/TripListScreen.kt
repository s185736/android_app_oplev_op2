package com.project.oplevapp.ui.screen

import android.content.ContentValues
import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.R
import com.project.oplevapp.data.CountryRepository
import com.project.oplevapp.model.Country
import com.project.oplevapp.nav.Screen

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
                            val countryFromDb = document.data["country"] as String
                            val city = document.data["city"] as String
                            val departureDate = document.data["departureDate"] as String
                            val returnDate = document.data["returnDate"] as String
                            val imageUrl = document.data["imageUrl"] as String
                            val info = document.data["info"] as String
                            countries.add(
                                Country(
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
            cells = GridCells.Fixed(2), contentPadding = PaddingValues(start = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ){
            items(list){ country ->
                CountryCard(country = country)
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

        /*
        Spacer(Modifier.height(16.dp))
        Row() {
            Image(
                painter = painterResource(id = R.drawable.copenhagen),
                contentDescription = "Copenhagen",
                modifier = Modifier.size(150.dp)
            )
            Spacer(Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.copenhagen),
                contentDescription = "Copenhagen",
                modifier = Modifier.size(150.dp)
            )
        }


        Spacer(Modifier.width(32.dp))
        Row() {
            Image(
                painter = painterResource(id = R.drawable.copenhagen),
                contentDescription = "Copenhagen",
                modifier = Modifier.size(150.dp)
            )
            Spacer(Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.copenhagen),
                contentDescription = "Copenhagen",
                modifier = Modifier.size(150.dp)
            )
        }


         */






    }
}

@Composable
fun CountryCard(country: Country){

Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp),
    shape = RoundedCornerShape(15.dp),
    elevation = 5.dp
) {
    Box(modifier = Modifier.height(250.dp)){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = rememberAsyncImagePainter(model = country.imageUrl),
            contentDescription = " ",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.TopStart
        ){
           Column {
               Text(
                   text = country.country,
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