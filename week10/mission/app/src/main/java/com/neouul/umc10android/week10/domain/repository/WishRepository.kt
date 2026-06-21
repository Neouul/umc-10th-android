package com.neouul.umc10android.week10.domain.repository

import com.neouul.umc10android.week10.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface WishRepository {
    fun getWishedProductsFlow(): Flow<List<Product>>
    suspend fun getWishedProducts(): List<Product>

    suspend fun addWishedProduct(product: Product)
    suspend fun removeWishedProduct(product: Product)
}