package com.hollander.template.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error


    fun launchWithState(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                block(this)
            } catch (e: Exception) {
                _error.value = "${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block(this)
            } catch (e: Exception) {
                _error.value = "${e.message}"
            }
        }
    }
}