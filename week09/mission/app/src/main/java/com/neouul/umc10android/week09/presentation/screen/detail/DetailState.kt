package com.neouul.umc10android.week09.presentation.screen.detail

import com.neouul.umc10android.week09.core.base.UiState
import com.neouul.umc10android.week09.domain.model.Product

data class DetailState(
    val product: Product? = null,
    val isLoading: Boolean = false
) : UiState