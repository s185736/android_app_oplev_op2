package com.project.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.project.myapplication.ui.theme.ApplicationTheme

class AddCountryActivity : ComponentActivity() {

    /*Creating Varibales*/
    private lateinit var question3: TextView            //global variable
    private lateinit var next_button: Button            //button to move to next activity
    private lateinit var previous_button: Button        //button to move to previous activity

    private fun ComponentActivity.onCreate(savedInstanceState: Bundle?) {
        onCreate(savedInstanceState)
        setContent {
            ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting3("")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcountry)
        //Using components from the xml file, and findViewById for buttons.
        next_button = findViewById(R.id.second_activity_next_button)
        previous_button = findViewById(R.id.second_activity_previous_button)
        question3 = findViewById(R.id.question3_id)
        //Using variable to be shown on the page.
        question3.text = "Page 3 - AddCountry...".trimIndent()
        //Implementing clicklistener
        next_button.setOnClickListener {
            //We're going to start the following activity.
            val intent = Intent(this, CountryPageActivity::class.java)
            //Starting the activity.
            startActivity(intent)
        }

        //Implementing clicklistener
        previous_button.setOnClickListener {
            //We're going to start the following activity.
            val intent = Intent(this, ListOfTripsActivity::class.java)
            //Starting the activity.
            startActivity(intent)
        }
    }
}

@Composable
fun Greeting3(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ApplicationTheme {
        Greeting3("Android")
    }
}