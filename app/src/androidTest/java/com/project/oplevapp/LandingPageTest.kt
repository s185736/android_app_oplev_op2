package com.project.oplevapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.project.oplevapp.view.MainActivity
import org.junit.Rule
import org.junit.Test

class LandingPageTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun buttonTest(){
        //Her testes om "Login side" knappen er vist og kan klikkes på vore landing page.
        val button = composeTestRule.onNodeWithText("Login side")
        button.assertIsDisplayed()
        button.performClick()
    }
}