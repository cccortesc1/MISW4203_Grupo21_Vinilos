package com.uniandes.miso.vinyls.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.uniandes.miso.vinyls.mockdata.MockData
import com.uniandes.miso.vinyls.models.Collector
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class CollectorsScreenKtTest{

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCollectorScreen() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            val collectorList = MutableLiveData<List<Collector>>()

            collectorList.value = MockData().collectorData()
            CollectorsScreen(navController = navController, collectorList)
        }
    }

    @Test
    fun collectors_list_is_displayed_successfully()  {
        composeTestRule.onNodeWithTag("collectorList").assertIsDisplayed()
    }

    @Test
    fun collectors_list_navigate_to16() : Unit  = with(composeTestRule){
        onNode(hasScrollAction()).performScrollToIndex(8)
        Thread.sleep(4000)
    }

}
