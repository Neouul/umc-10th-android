package com.neouul.umc10android.week10.presentation.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.neouul.umc10android.week10.core.base.BaseViewModel
import com.neouul.umc10android.week10.core.routing.Route
import com.neouul.umc10android.week10.domain.repository.ProductRepository
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
            updateState { it.copy(isLoading = true) }
            productRepository.getProduct(productId).collect { product ->
                updateState {
                    it.copy(
                        product = product,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun toggleWish() {
        val currentProduct = uiState.value.product ?: return
        viewModelScope.launch {
            productRepository.updateWishStatus(currentProduct.id, !currentProduct.isWished)
        }
    }
}