package com.neouul.umc10android.week08.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.neouul.umc10android.week08.core.base.BaseViewModel
import com.neouul.umc10android.week08.core.routing.Route
import com.neouul.umc10android.week08.domain.model.Product
import com.neouul.umc10android.week08.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailState>(DetailState()) {

    private val productId = savedStateHandle.toRoute<Route.ProductDetail>().productId

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            productRepository.getProduct(productId).collect { product ->
                _uiState.value = _uiState.value.copy(
                    product = product,
                    isLoading = false
                )
            }
        }
    }

    fun toggleWish() {
        val currentProduct = _uiState.value.product ?: return
        viewModelScope.launch {
            productRepository.updateWishStatus(currentProduct.id, !currentProduct.isWished)
        }
    }
}