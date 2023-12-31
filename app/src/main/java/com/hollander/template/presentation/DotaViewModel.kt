package com.hollander.template.presentation

import android.app.Notification.Action
import androidx.lifecycle.ViewModel
import com.hollander.template.data.remote.repository.Hero
import com.hollander.template.domain.repository.DotaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DotaViewModel @Inject constructor(
    private val dotaRepository: DotaRepository
) : ViewModel() {

    private val _action = MutableStateFlow<Action>(Action.IsLoading)
    val action get() = _action

    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val heroes = dotaRepository.getHeroes()
                _action.value = Action.ShowHeroes(heroes)
                Timber.d("${heroes.size} heroes loaded successfully")
            } catch (e: Exception) {
                _action.value = Action.Error
            }
        }
    }

    sealed interface Action {
        data class ShowHeroes(val heroes: List<Hero>) : Action
        data object IsLoading : Action
        data object Error : Action
    }
}