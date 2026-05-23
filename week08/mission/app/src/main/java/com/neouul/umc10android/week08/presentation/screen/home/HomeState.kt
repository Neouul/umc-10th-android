package com.neouul.umc10android.week08.presentation.screen.home

import com.neouul.umc10android.week08.core.base.UiState
import com.neouul.umc10android.week08.domain.model.Product

data class HomeState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false
) : UiState