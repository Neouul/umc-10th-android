package com.neouul.umc10android.week09.presentation.screen.wish

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.neouul.umc10android.week09.domain.model.Product

@Composable
fun WishRoot(
    onNavigateToDetail: (Product) -> Unit = {},
    viewModel: WishViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WishScreen(
        uiState = uiState,
        onWishClick = { product -> viewModel.toggleWish(product) },
        onProductClick = onNavigateToDetail
    )
}
