package com.neouul.umc10android.week10.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<STATE : UiState>(
    initialPageState: STATE,
) : ViewModel() {
    protected val _uiState = MutableStateFlow(initialPageState)
    val uiState: StateFlow<STATE> = _uiState.asStateFlow()

    protected fun updateState(update: (STATE) -> STATE) {
        _uiState.update { update(it) }
    }
}