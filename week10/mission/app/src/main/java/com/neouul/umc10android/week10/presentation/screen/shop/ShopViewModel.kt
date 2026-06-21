package com.neouul.umc10android.week10.presentation.screen.shop

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week10.core.base.BaseViewModel
import com.neouul.umc10android.week10.domain.model.Product
import com.neouul.umc10android.week10.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel<ShopState>(ShopState()) {

    private val _selectedTabIndex = MutableStateFlow(0)

    init {
        combine(
            productRepository.getAllProducts(),
            _selectedTabIndex
        ) { products, index ->
            val filtered = when (index) {
                1 -> products.filter { it.category.contains("Tops", ignoreCase = true) }
                2 -> products.filter { it.isBestSeller }
                else -> products
            }
            ShopState(
                products = filtered,
                selectedTabIndex = index
            )
        }.onEach { state ->
            updateState { state }
        }.launchIn(viewModelScope)
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
