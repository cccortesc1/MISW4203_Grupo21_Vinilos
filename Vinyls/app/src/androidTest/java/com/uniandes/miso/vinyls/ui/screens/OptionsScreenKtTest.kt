package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Rule
import org.junit.Test


internal class OptionsScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController


    @Test
    fun testCollectorOption() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            OptionsScreen(navController = navController, "coleccionista")

        }
        composeTestRule.onNodeWithTag("Albumes").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Artistas").assertIsDisplayed()
    }

    @Test
    fun testGuestOption() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            OptionsScreen(navController = navController, "invitado")
        }
        composeTestRule.onNodeWithTag("Albumes").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Artistas").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Coleccionistas").assertIsDisplayed()
    }
}