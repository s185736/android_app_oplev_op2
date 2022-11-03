package com.project.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.myapplication.ui.theme.ApplicationTheme

class ListOfTripsActivity : ComponentActivity() {

    /*Creating Varibales*/
    private lateinit var question2: TextView            //global variable
    private lateinit var next_button: Button            //button to move to next activity
    private lateinit var previous_button: Button        //button to move to previous activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listoftrips)
        //Using components from the xml file, and findViewById for buttons.
        next_button = findViewById(R.id.second_activity_next_button)
        previous_button = findViewById(R.id.second_activity_previous_button)
        question2 = findViewById(R.id.question2_id)
        //Using variable to be shown on the page.
        question2.text = "Page 2 - ListOfTrips...".trimIndent()
        //Implementing clicklistener
        next_button.setOnClickListener {
            //We're going to start the following activity.
            val intent = Intent(this, AddCountryActivity::class.java)
            //Starting the activity.
            startActivity(intent)
        }

        //Implementing clicklistener
        previous_button.setOnClickListener {
            //We're going to start the following activity.
            val intent = Intent(this, LandingPageActivity::class.java)
            //Starting the activity.
            startActivity(intent)
        }
    }
}
@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ApplicationTheme {
        Greeting2("Android")
    }
}