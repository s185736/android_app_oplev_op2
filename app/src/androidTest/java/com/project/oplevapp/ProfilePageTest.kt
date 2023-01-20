package com.project.oplevapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.project.oplevapp.model.repository.UserRepository
import com.project.oplevapp.view.ui.screen.profile.Profile
import com.project.oplevapp.view.ui.theme.OplevAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfilePageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            OplevAppTheme {
                Profile(navController = rememberNavController(), userRepository = UserRepository())
            }
        }
    }

    @Test
    fun buttonTest(){
        val button = composeTestRule.onNodeWithTag("updateButton")
        button.assertIsDisplayed()
    }
    @Test
    fun textFieldTest(){
        val name = composeTestRule.onNodeWithText("Navn")
        name.assertIsDisplayed()
    }
}
