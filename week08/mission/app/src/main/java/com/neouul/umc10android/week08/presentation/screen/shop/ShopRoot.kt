package com.neouul.umc10android.week08.presentation.screen.shop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ShopRoot(
    viewModel: ShopViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ShopScreen(
        uiState = uiState,
        onTabSelected = viewModel::onTabSelected,
        onWishClick = viewModel::toggleWish
    )
}
