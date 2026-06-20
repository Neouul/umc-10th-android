package com.neouul.umc10android.week10.presentation.screen.shop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neouul.umc10android.week10.domain.model.Product

@Composable
fun ShopRoot(
    onNavigateToDetail: (Product) -> Unit = {},
    viewModel: ShopViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ShopScreen(
        uiState = uiState,
        onTabSelected = viewModel::onTabSelected,
        onWishClick = viewModel::toggleWish,
        onProductClick = onNavigateToDetail
    )
}
