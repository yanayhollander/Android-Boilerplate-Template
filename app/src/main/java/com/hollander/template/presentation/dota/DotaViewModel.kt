package com.hollander.template.presentation.dota

import com.hollander.template.data.dto.Hero
import com.hollander.template.domain.repository.DatabaseRepository
import com.hollander.template.domain.repository.DotaRepository
import com.hollander.template.presentation.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DotaViewModel @Inject constructor(
    private val dotaRepository: DotaRepository,
    private val databaseRepository: DatabaseRepository
) : BaseViewModel() {

    private val _action = MutableStateFlow<UiState>(UiState.Initial)
    val action get() = _action

    init {
        launchWithState {
            val heroes = dotaRepository.getHeroes()
            _action.value = UiState.ShowHeroes(heroes)
            databaseRepository.saveHeroes(heroes, force = true)
            Timber.d("${heroes.size} heroes loaded successfully")
        }
    }

    fun getHero(heroId: Int?): Hero {
        when (_action.value) {
            is UiState.ShowHeroes -> {
                val heroes = (_action.value as UiState.ShowHeroes).heroes
                return heroes.first { it.id == heroId }
            }

            else -> {
                throw Exception("Heroes not loaded")
            }
        }
    }

    sealed interface UiState {
        data object Initial : UiState
        data class ShowHeroes(val heroes: List<Hero>) : UiState
    }
}