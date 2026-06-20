package com.neouul.umc10android.week10.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<STATE : UiState>(
    initialPageState: STATE,
) : ViewModel() {
    protected val _uiState = MutableStateFlow(initialPageState)
    val uiState: StateFlow<STATE>
        get() = _uiState.asStateFlow()

    protected fun updateState(update: (STATE) -> STATE) {
        _uiState.value = update(_uiState.value)
    }
}