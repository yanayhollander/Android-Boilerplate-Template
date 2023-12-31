package com.hollander.template.presentation

import android.app.Notification.Action
import androidx.lifecycle.ViewModel
import com.hollander.template.data.remote.repository.Hero
import com.hollander.template.domain.repository.DotaRepository
import com.hollander.template.presentation.viewModel.AsyncViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class DotaViewModel @Inject constructor(
    private val dotaRepository: DotaRepository
) : AsyncViewModel() {

    private val _action = MutableStateFlow<Action?>(null)
    val action get() = _action

    init {
        launchWithState {
            val heroes = dotaRepository.getHeroes()
            _action.value = Action.ShowHeroes(heroes)
            Timber.d("${heroes.size} heroes loaded successfully")
        }
    }

    sealed interface Action {
        data class ShowHeroes(val heroes: List<Hero>) : Action
    }
}