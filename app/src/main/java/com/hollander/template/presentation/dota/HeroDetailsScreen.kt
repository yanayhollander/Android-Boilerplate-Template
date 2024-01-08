package com.hollander.template.presentation.dota

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.hollander.template.R
import com.hollander.template.data.dto.Hero
import com.hollander.template.presentation.composables.ErrorComposable
import com.hollander.template.presentation.composables.Loading
import com.hollander.template.ui.theme.AndroidTemplateTheme

@Composable
internal fun HeroDetailsRoute(
    viewModel: DotaViewModel = hiltViewModel<DotaViewModel>(),
    heroId: Int?
) {

    val isLoading = viewModel.isLoading.observeAsState(false)
    val error = viewModel.error.observeAsState()
    val hero = viewModel.getHero(heroId)

    HeroDetailsScreen(
        isLoading = isLoading.value,
        error = error.value,
        hero = hero
    )
}

@Composable
fun HeroDetailsScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    error: String?,
    hero: Hero
) {

    if (isLoading) {
        Loading()
        return
    }

    error?.let {
        ErrorComposable(message = it)
        return
    }

    Box(modifier = modifier.fillMaxSize()){
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(hero.photoUrl)
                .scale(Scale.FILL)
                .crossfade(true)
                .build()
        )

        Column {
            Image(
                painter = painter,
                contentDescription = hero.localizedName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Column(modifier = Modifier.padding(32.dp)){
                Text(
                    text = hero.localizedName,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(text = hero.localizedName)
                Button(onClick = {  }){
                    Text(text = "Watch")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HeroDetailsPreview() {
    AndroidTemplateTheme {
        HeroDetailsScreen(
            isLoading = false,
            error = null,
            hero = Hero(
                id = 1,
                name = "Anti-Mage",
                localizedName = "Anti-Mage",
                primaryAttr = "agi",
                attackType = "Melee",
                roles = listOf("Carry", "Escape", "Nuker"),
            )
        )
    }
}