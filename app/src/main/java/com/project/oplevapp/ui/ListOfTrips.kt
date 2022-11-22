package com.project.oplevapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.project.oplevapp.R
import com.project.oplevapp.ui.theme.OplevAppTheme

/*
class ListOfTrips : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OplevAppTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }

 */



//for at afprøve hvordan det var lavet til lektion 7 øvelse
class ListOfTrips : ViewModel(){

    @Composable
    fun ListOfTripsScreen() {
        var tipInput by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Destination",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.height(16.dp))
            SearchBar(
                label = R.string.Search,
                value = tipInput,
                onValueChanged = { tipInput = it }
            )
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

            )
    }

    @Preview(showBackground = true)
    @Composable
    fun TripsPreview() {
        OplevAppTheme {
            ListOfTripsScreen()
        }
    }

}

