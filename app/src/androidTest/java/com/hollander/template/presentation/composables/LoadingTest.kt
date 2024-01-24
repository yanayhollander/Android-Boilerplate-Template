package com.hollander.template.presentation.composables


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoadingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingComposableTest() {
        // Launch the composable
        composeTestRule.setContent {
            Loading()
        }

        // Assert the presence of CircularProgressIndicator
        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertIsDisplayed()
    }
}