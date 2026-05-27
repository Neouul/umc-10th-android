package com.neouul.umc10android.week09.presentation.screen.shop

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week09.core.base.BaseViewModel
import com.neouul.umc10android.week09.domain.model.Product
import com.neouul.umc10android.week09.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel<ShopState>(ShopState()) {

    private val _selectedTabIndex = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            productRepository.getAllProducts()
                .combine(_selectedTabIndex) { products, index ->
                    products to index
                }.collect { (products, index) ->
                    val filtered = when (index) {
                        1 -> products.filter { it.category.contains("Tops", ignoreCase = true) }
                        2 -> products.filter { it.isBestSeller }
                        else -> products
                    }
                    _uiState.value = _uiState.value.copy(
                        products = filtered,
                        selectedTabIndex = index
                    )
                }
        }
    }

    fun onTabSelected(index: Int) {
        _selectedTabIndex.value = index
    }

    fun toggleWish(product: Product) {
        viewModelScope.launch {
            productRepository.updateWishStatus(product.id, !product.isWished)
        }
    }
}
