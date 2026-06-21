package com.neouul.umc10android.week10.presentation.screen.detail

import com.neouul.umc10android.week10.core.base.UiState
import com.neouul.umc10android.week10.domain.model.Product

data class DetailState(
    val product: Product? = null,
    val isLoading: Boolean = false
) : UiState