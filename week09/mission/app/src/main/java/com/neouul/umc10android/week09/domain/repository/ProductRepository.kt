package com.neouul.umc10android.week09.domain.repository

import com.neouul.umc10android.week09.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<List<Product>>
    fun getHomeProducts(): Flow<List<Product>>
    fun getProduct(productId: Long): Flow<Product?>
    suspend fun updateProduct(product: Product)
    suspend fun updateWishStatus(productId: Long, isWished: Boolean)
    suspend fun syncProducts()
}