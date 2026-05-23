package com.neouul.umc10android.week08.presentation.screen.wish

import com.neouul.umc10android.week08.core.base.UiState
import com.neouul.umc10android.week08.domain.model.Product

data class WishState(
    val products: List<Product> = emptyList()
) : UiState