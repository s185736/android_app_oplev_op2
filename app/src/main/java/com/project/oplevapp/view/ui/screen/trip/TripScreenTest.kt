package com.project.oplevapp.view.ui.screen.trip

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.project.oplevapp.model.data.trip.TripDataProviderTest

@Composable
fun TripScreenTestContent(){
    val trips = remember { TripDataProviderTest.tripListTest}
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)){
        items(
            items = trips,
            itemContent = {
                 TripListItem(tripInfo = it)
            }
        )
    }
}
