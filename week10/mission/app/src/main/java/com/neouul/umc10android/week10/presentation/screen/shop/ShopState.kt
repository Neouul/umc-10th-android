package com.neouul.umc10android.week10.presentation.screen.shop

import com.neouul.umc10android.week10.core.base.UiState
import com.neouul.umc10android.week10.domain.model.Product

data class ShopState(
    val products: List<Product> = emptyList(),
    val selectedTabIndex: Int = 0,
    val tabs: List<String> = listOf("전체", "Tops & T-Shirts", "sale")
) : UiState