package com.project.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CountryPageActivity : AppCompatActivity() {

    /*Creating Varibales*/
    private lateinit var question4: TextView            //global variable
    private lateinit var next_button: Button            //button to move to next activity
    private lateinit var previous_button: Button        //button to move to previous activity

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countrypage)
        //Using components from the xml file, and findViewById for buttons.
        next_button = findViewById(R.id.second_activity_next_button)
        previous_button = findViewById(R.id.second_activity_previous_button)
        question4 = findViewById(R.id.question4_id)
        //Using variable to be shown on the page.
        question4.text = "Page 4 - CountryPage...".trimIndent()
        //Implementing clicklistener
        next_button.setOnClickListener {
            //We're going to start the following activity.
            val intent = Intent(this, ShareboardsActivity::class.java)
            //Starting the activity.
            startActivity(intent)
        }

        //Implementing clicklistener
        previous_button.setOnClickListener {
            //We're going to start the following activity.
            val intent = Intent(this, AddCountryActivity::class.java)
            //Starting the activity.
            startActivity(intent)
        }
    }
}
