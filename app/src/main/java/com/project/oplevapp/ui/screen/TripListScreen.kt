package com.project.oplevapp.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.oplevapp.R
import com.project.oplevapp.model.TripInfo
import com.project.oplevapp.nav.Screen
import com.project.oplevapp.ui.screen.trip.TripScreenTestContent

@Composable
fun TripListScreen(
    navController: NavController,
    //onDestinationBtnClicked: () -> Unit = {},
    modifier: Modifier = Modifier
){
    var searchBar by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Destination",
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(16.dp))
        SearchBar(
            label = R.string.Search,
            value = searchBar,
            onValueChanged = { searchBar = it }
        )
        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(
                onClick = { navController.navigate(Screen.AddCountry.route) }
            ) {
                Text(text = "Tilføj rejse")
            }
            Button(
                onClick = { navController.navigate(Screen.Country.route) },
                modifier = Modifier.align(CenterVertically)
            ) {
                Text(text = "København1")
            }
        }

        TripScreenTestContent()

        //Spacer(Modifier.height(16.dp))
        //Skal lave en funktion hvor der tages oplysninger gennem appen, når der oprettes ny rejse.
        //TODO skal tilføje knap, så der kan navigeres til den rejse der vil se mere info på.
        val listTripInfo = listOf<TripInfo>(
            TripInfo(1,"København0", R.drawable.copenhagen),
            TripInfo(2,"test1", R.drawable.copenhagen),
            TripInfo(3,"test2", R.drawable.copenhagen),
        )
        DisplayTrips(listTrips = listTripInfo)


            Row() {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.copenhagen),
                        contentDescription = "Copenhagen",
                        modifier = Modifier.size(150.dp)
                    )
                    Button(
                        onClick = { navController.navigate(Screen.AddCountry.route) },
                        modifier = Modifier.align(CenterHorizontally)
                    ) {
                        Text(text = "Tilføj rejse")
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.copenhagen),
                        contentDescription = "Copenhagen",
                        modifier = Modifier.size(150.dp)
                    )
                    Button(
                        onClick = { navController.navigate(Screen.Country.route) },
                        modifier = Modifier.align(CenterHorizontally)
                    ) {
                        Text(text = "København1")
                    }
                }
            }
        Row() {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.copenhagen),
                        contentDescription = "Copenhagen",
                        modifier = Modifier.size(150.dp)
                    )
                    Button(
                        onClick = { navController.navigate(Screen.Country.route) },
                        modifier = Modifier.align(CenterHorizontally)
                    ) {
                        Text(text = "København2")
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.copenhagen),
                        contentDescription = "Copenhagen",
                        modifier = Modifier.size(150.dp)
                    )
                    Button(
                        onClick = { navController.navigate(Screen.Country.route) },
                        modifier = Modifier.align(CenterHorizontally)
                    ) {
                        Text(text = "København3")
                    }
                }
            }
            Row() {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.copenhagen),
                        contentDescription = "Copenhagen",
                        modifier = Modifier.size(150.dp)
                    )
                    Button(
                        onClick = { navController.navigate(Screen.Country.route) },
                        modifier = Modifier.align(CenterHorizontally)
                    ) {
                        Text(text = "København4")
                    }
                }
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.copenhagen),
                        contentDescription = "Copenhagen",
                        modifier = Modifier.size(150.dp)
                    )
                    Button(
                        onClick = { navController.navigate(Screen.Country.route) },
                        modifier = Modifier.align(CenterHorizontally)
                    ) {
                        Text(text = "København5")
                    }
                }
            }
        Row() {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.copenhagen),
                    contentDescription = "Copenhagen",
                    modifier = Modifier.size(150.dp)
                )
                Button(
                    onClick = { navController.navigate(Screen.Country.route) },
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(text = "København6")
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.copenhagen),
                    contentDescription = "Copenhagen",
                    modifier = Modifier.size(150.dp)
                )
                Button(
                    onClick = { navController.navigate(Screen.Country.route) },
                    modifier = Modifier.align(CenterHorizontally)
                ) {
                    Text(text = "København7")
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
        onValueChange = onValueChanged,
        label = { Text(stringResource(label)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        )
        )
}

@Preview(showBackground = true)
@Composable
fun TripsPreview() {
    TripListScreen(rememberNavController());
}