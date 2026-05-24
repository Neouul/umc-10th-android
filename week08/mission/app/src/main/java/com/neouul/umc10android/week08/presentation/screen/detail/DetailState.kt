package com.neouul.umc10android.week08.presentation.screen.detail

import com.neouul.umc10android.week08.core.base.UiState
import com.neouul.umc10android.week08.domain.model.Product

data class DetailState(
    val product: Product? = null,
    val isLoading: Boolean = false
) : UiState