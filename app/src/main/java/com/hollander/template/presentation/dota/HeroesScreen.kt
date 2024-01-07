package com.hollander.template.presentation.dota

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.hollander.template.data.dto.Hero
import com.hollander.template.presentation.composables.ErrorComposable
import com.hollander.template.presentation.composables.Loading
import com.hollander.template.ui.theme.AndroidTemplateTheme
import timber.log.Timber

@Composable
internal fun HeroesRoute(
    viewModel: DotaViewModel = hiltViewModel<DotaViewModel>(),
    onItemClicked: (String) -> Unit
) {
    val action by viewModel.action.collectAsState()
    val isLoading = viewModel.isLoading.observeAsState(false)
    val error = viewModel.error.observeAsState()

    HeroesScreen(
        isLoading = isLoading.value,
        error = error.value,
        action = action,
        onHeroClicked = { Timber.d("Hero clicked: $it") }
    )
}

@Composable
fun HeroesScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    action: DotaViewModel.UiState,
    error: String?,
    onHeroClicked: (String) -> Unit = {},
) {

    if (isLoading) {
        Loading()
        return
    }

    error?.let {
        ErrorComposable(message = it)
        return
    }

    when (action) {
        is DotaViewModel.UiState.ShowHeroes -> {
            BoxWithConstraints {
                LazyColumn(modifier = modifier.padding(vertical = 8.dp, horizontal = 8.dp)) {
                    items(action.heroes) {
                        HeroCard(
                            modifier = modifier.padding(4.dp),
                            hero = it,
                            onClick = { onHeroClicked(it.localizedName) }
                        )
                    }
                }
            }
        }

        DotaViewModel.UiState.Initial -> {}
    }
}


@Composable
fun HeroCard(modifier: Modifier = Modifier, hero: Hero, onClick: () -> Unit = {}) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Row(
            modifier = modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
        ) {
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(hero.photoUrl)
                    .scale(Scale.FILL)
                    .crossfade(true)
                    .build()
            )

            Image(
                painter = painter,
                contentDescription = "Hero Image",
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(Color.LightGray)
                    .width(125.dp)
                    .height(70.dp)
            )

            Text(
                text = hero.localizedName,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeroCardPreview() {
    AndroidTemplateTheme {
        HeroCard(
            hero = Hero(
                1,
                "Anti-Mage",
                "Anti-Mage",
                "agi",
                "meele",
                listOf("carry", "escape", "nuker")
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingStatePreview() {
    AndroidTemplateTheme {
        HeroesScreen(
            isLoading = true,
            error = null,
            action = DotaViewModel.UiState.Initial
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesListPreview() {
    AndroidTemplateTheme {
        HeroesScreen(
            isLoading = false,
            error = null,
            action = DotaViewModel.UiState.ShowHeroes(
                listOf(
                    Hero(
                        1,
                        "Anti-Mage",
                        "Anti-Mage",
                        "agi",
                        "meele",
                        listOf("carry", "escape", "nuker"),
                    ),
                    Hero(
                        1,
                        "Treant Protector",
                        "Treant Protector",
                        "str",
                        "meele",
                        listOf("support", "disabler", "nuker", "durable", "escape")
                    )
                )
            )
        )
    }
}