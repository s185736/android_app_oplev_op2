package com.project.oplevapp.data.dbTest

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.project.oplevapp.R
/*
private var selectedPlant: User? = null
private val viewModel: UserViewModel by viewModel  viewModel<MainViewModel>()
private var inPlantName: String = ""

@Composable
fun UserScreen(/*
    navController: NavController,
    onCreateBtnClicked: () -> Unit = {},
    modifier: Modifier = Modifier
    */
    firstName: String, lastName : List<User> = ArrayList<User>()
) {





    var inLocation by remember { mutableStateOf("") }
    var inDescription by remember { mutableStateOf("") }
    var inDatePlanted by remember { mutableStateOf("") }
    val context = LocalContext.current

    OutlinedTextField(
        value = inLocation,
        onValueChange = { inLocation = it },
        label = { "location" },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = inDescription,
        onValueChange = { inDescription = it },
        label = { "description" },
        modifier = Modifier.fillMaxWidth()
    )
    OutlinedTextField(
        value = inDatePlanted,
        onValueChange = { inDatePlanted = it },
        label = { "date" },
        modifier = Modifier.fillMaxWidth()
    )
    Button(
        onClick = {
            var user = User().apply {
                firstName =

            }

            var specimen = Specimen().apply {
                plantName = inPlantName
                plantId = selectedPlant?.let {
                    it.id
                } ?: 0
                location = inLocation
                description = inDescription
                datePlanted = inDatePlanted
            }
            viewModel.save(specimen)
            Toast.makeText(
                context,
                "$inPlantName $inLocation $inDescription $inDatePlanted",
                Toast.LENGTH_LONG
            ).show()
        }
    ) {
        Text(text = "Save")
    }
}



@Preview(name = "Light Mode", showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode")
@Composable
fun DefaultPreview() {
    MyPlantDiaryTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.fillMaxWidth()
        ) {
            SpecimenFacts("Android")
        }
    }
}

 */