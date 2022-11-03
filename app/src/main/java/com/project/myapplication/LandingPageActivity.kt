package com.project.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.project.myapplication.ui.theme.ApplicationTheme

class LandingPageActivity : ComponentActivity() {

    /*Creating Varibales*/
    private lateinit var question1: TextView            //global variable
    private lateinit var next_Activity_button: Button   //button to move to next activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landingpage)
        //Using components from the xml file, and findViewById for buttons.
        next_Activity_button = findViewById(R.id.first_activity_next_button)
        question1 = findViewById(R.id.question1_id)
        //Using variable to be shown on the page.
        question1.text = "Page 1 - LandingPage...".trimIndent()
        //Implementing clicklistener
        next_Activity_button.setOnClickListener {
            //We're going to start the following activity.
            val intent = Intent(this, ListOfTripsActivity::class.java)
            //Starting the activity.
            startActivity(intent)
        }
    }
}
@Composable
fun Greeting1(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
    ApplicationTheme {
        Greeting1("Android")
    }
}