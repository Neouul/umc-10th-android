package com.neouul.umc10android.week08.data.data_source

import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    fun getTotalProducts(): Flow<String>
    fun getHomeProducts(): Flow<String>
    fun getWishProducts(): Flow<String>

    suspend fun updateTotalProduct(products: String)
    suspend fun updateHomeProduct(products: String)
    suspend fun updateWishProduct(products: String)
}
