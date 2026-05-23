package com.neouul.umc10android.week08.data.data_source

import com.neouul.umc10android.week08.data.data_source.local.entity.ProductWithWish
import com.neouul.umc10android.week08.data.data_source.local.entity.ProductEntity
import com.neouul.umc10android.week08.data.data_source.local.entity.WishEntity
import kotlinx.coroutines.flow.Flow

interface ProductDataSource {
    fun getAllProducts(): Flow<List<ProductWithWish>>
    fun getProductsByIds(ids: List<Long>): Flow<List<ProductWithWish>>
    fun getWishedProducts(): Flow<List<ProductWithWish>>

    suspend fun insertProducts(products: List<ProductEntity>)
    suspend fun updateWishStatus(productId: Long, isWished: Boolean)
    suspend fun getProductCount(): Int
}
