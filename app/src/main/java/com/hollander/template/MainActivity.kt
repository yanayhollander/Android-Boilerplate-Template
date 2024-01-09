package com.hollander.template

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hollander.template.presentation.dota.DotaViewModel
import com.hollander.template.presentation.dota.HeroDetailsRoute
import com.hollander.template.presentation.dota.HeroesRoute
import com.hollander.template.routes.Routes
import com.hollander.template.ui.theme.AndroidTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AndroidTemplateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: DotaViewModel = hiltViewModel<DotaViewModel>()

                    NavHost(
                        navController = navController,
                        startDestination = Routes.HeroesList
                    ) {
                        composable(Routes.HeroesList) {
                            HeroesRoute(
                                viewModel = viewModel,
                                onItemClicked = {
                                    navController.navigate(
                                        Routes.HeroDetails.replace(
                                            "{heroId}",
                                            it.id.toString()
                                        )
                                    )
                                })
                        }
                        composable(
                            Routes.HeroDetails,
                            arguments = listOf(navArgument("heroId") { type = NavType.IntType })
                        ) {
                            HeroDetailsRoute(
                                viewModel = viewModel,
                                heroId = it.arguments?.getInt("heroId")
                            )
                        }
                    }
                }
            }
        }
    }
}
