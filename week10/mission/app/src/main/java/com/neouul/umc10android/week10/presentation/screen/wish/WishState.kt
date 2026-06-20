package com.neouul.umc10android.week10.presentation.screen.wish

import com.neouul.umc10android.week10.core.base.UiState
import com.neouul.umc10android.week10.domain.model.Product

data class WishState(
    val products: List<Product> = emptyList()
) : UiState