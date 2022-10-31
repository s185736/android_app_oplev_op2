package com.project.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShareboardsActivity : AppCompatActivity() {

    /*Creating Varibales*/
    private lateinit var question5: TextView            //global variable
    private lateinit var previous_button: Button        //button to move to next activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shareboards)
        //Using components from the xml file, and findViewById for buttons.
        previous_button = findViewById(R.id.third_activity_previous_button)
        question5 = findViewById(R.id.question5_id)
        //Using variable to be shown on the page.
        question5.text = "Page 5 - Shareboards...".trimIndent()
        //Implementing clicklistener
        previous_button.setOnClickListener {
            //We're going to start the following activity.
            val intent = Intent(this, CountryPageActivity::class.java)
            //Starting the activity.
            startActivity(intent)
        }
    }
}
