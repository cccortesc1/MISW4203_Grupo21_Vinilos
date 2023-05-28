package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.uniandes.miso.vinyls.mockdata.MockData
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class CollectorDetailScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCollectorScreen() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            val collectorDetail  = MockData().collectorData()[0]
            CollectorDetailScreen(navController = navController, collectorDetail)
        }
    }

    @Test
    fun collectors_detail_screen_displayed_successfully()  {
        composeTestRule.onNodeWithTag("collectorDetailScreen").assertIsDisplayed()
        Thread.sleep(4000)
    }
}