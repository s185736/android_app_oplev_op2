package com.project.oplevapp.view.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.oplevapp.model.data.trip.TripInfo

//https://www.youtube.com/watch?v=f6b0ehBhbiQ
@Composable
private fun TripsUI(tripInfo: TripInfo){
    Row(modifier = Modifier
        .padding(20.dp)
        .fillMaxSize()
        .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Column {
            Text(text = "Destination: ${tripInfo.name}",
            fontSize = 24.sp)
            Image(painter = painterResource(id = tripInfo.image), contentDescription = "")
        }
    }
}

@Composable
fun DisplayTrips(listTrips: List<TripInfo>){
    LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)){
        items(items = listTrips){
            Trip -> TripsUI(tripInfo = Trip)
        }
    }
}
