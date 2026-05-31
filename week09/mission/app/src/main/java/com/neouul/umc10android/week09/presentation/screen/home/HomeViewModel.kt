package com.neouul.umc10android.week09.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.neouul.umc10android.week09.core.base.BaseViewModel
import com.neouul.umc10android.week09.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel<HomeState>(HomeState()) {

    init {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            productRepository.syncProducts()
            updateState { it.copy(isLoading = false) }
        }

        viewModelScope.launch {
            productRepository.getHomeProducts().collect { products ->
                updateState { it.copy(products = products) }
            }
        }
    }
}
