package com.neouul.umc10android.week08.domain.repository

import com.neouul.umc10android.week08.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getTotalProducts(): Flow<List<Product>>
    fun getHomeProducts(): Flow<List<Product>>
    suspend fun updateTotalProduct(product: Product)
    suspend fun updateHomeProduct(product: Product)
    suspend fun initializeDataIfNeeded()
}