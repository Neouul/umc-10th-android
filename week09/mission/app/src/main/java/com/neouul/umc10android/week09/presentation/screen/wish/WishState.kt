package com.neouul.umc10android.week09.presentation.screen.wish

import com.neouul.umc10android.week09.core.base.UiState
import com.neouul.umc10android.week09.domain.model.Product

data class WishState(
    val products: List<Product> = emptyList()
) : UiState