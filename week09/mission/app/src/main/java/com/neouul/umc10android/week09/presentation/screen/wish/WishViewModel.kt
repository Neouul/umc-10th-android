package com.neouul.umc10android.week09.presentation.screen.wish

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week09.core.base.BaseViewModel
import com.neouul.umc10android.week09.domain.model.Product
import com.neouul.umc10android.week09.domain.repository.ProductRepository
import com.neouul.umc10android.week09.domain.repository.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishViewModel @Inject constructor(
    private val wishRepository: WishRepository,
    private val productRepository: ProductRepository
) : BaseViewModel<WishState>(WishState()) {

    init {
        viewModelScope.launch {
            wishRepository.getWishedProductsFlow().collect { products ->
                updateState { it.copy(products = products) }
            }
        }
    }

    fun toggleWish(product: Product) {
        viewModelScope.launch {
            productRepository.updateWishStatus(product.id, !product.isWished)
        }
    }
}
