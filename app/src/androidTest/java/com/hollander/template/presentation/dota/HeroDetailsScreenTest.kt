package com.hollander.template.presentation.dota

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.MutableLiveData
import com.hollander.template.data.dto.Hero
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class HeroDetailsScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun loadingState() {
        val viewModel: DotaViewModel = mockk()
        every { viewModel.isLoading } returns MutableLiveData(true)
        every { viewModel.error } returns MutableLiveData(null)
        every { viewModel.getHero(any()) } returns mockHero()

        rule.setContent {
            HeroDetailsRoute(viewModel = viewModel, heroId = 1)
        }

        rule.onNodeWithTag("CircularProgressIndicator").assertIsDisplayed()
    }

    @Test
    fun errorState() {
        val viewModel: DotaViewModel = mockk()
        every { viewModel.isLoading } returns MutableLiveData(false)
        every { viewModel.error } returns MutableLiveData("fakeError")
        every { viewModel.getHero(any()) } returns mockHero()

        rule.setContent {
            HeroDetailsRoute(viewModel = viewModel, heroId = 1)
        }

        rule.onNodeWithTag("CircularProgressIndicator").assertDoesNotExist()
        rule.onNodeWithTag("ErrorComposable").assertIsDisplayed()
        rule.onNodeWithText("fakeError", ignoreCase = true, substring = true).assertIsDisplayed()
    }

    private fun mockHero(): Hero {
        return Hero(1, "Anti-Mage", "Anti-Mage", "agi", "melee", listOf("carry", "escape", "nuker"))
    }
}