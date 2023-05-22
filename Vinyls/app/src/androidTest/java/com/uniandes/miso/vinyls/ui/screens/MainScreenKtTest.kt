package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class MainScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupVinylsNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreen(navController = navController)
        }
    }

    @Test
    fun mainScreenNavHost_verifyStartDestination() {
        composeTestRule.onNodeWithTag("mainScreen").assertIsDisplayed()
    }

    @Test
    fun mainScreen_verifyStartButtons() {
        composeTestRule.onNodeWithTag("Coleccionista").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Invitado").assertIsDisplayed()
    }
}