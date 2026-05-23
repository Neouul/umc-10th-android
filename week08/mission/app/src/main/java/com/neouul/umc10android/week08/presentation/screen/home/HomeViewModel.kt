package com.neouul.umc10android.week08.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week08.core.base.BaseViewModel
import com.neouul.umc10android.week08.domain.model.Product
import com.neouul.umc10android.week08.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel<HomeState>(HomeState()) {

    init {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            productRepository.syncProducts()
            _uiState.value = _uiState.value.copy(isLoading = false)
        }

        viewModelScope.launch {
            productRepository.getHomeProducts().collect { products ->
                _uiState.value = _uiState.value.copy(products = products)
            }
        }
    }
}
