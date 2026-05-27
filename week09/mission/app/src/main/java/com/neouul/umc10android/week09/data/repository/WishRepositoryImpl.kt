package com.neouul.umc10android.week09.data.repository

import com.neouul.umc10android.week09.data.data_source.ProductDataSource
import com.neouul.umc10android.week09.data.mapper.toDomain
import com.neouul.umc10android.week09.domain.model.Product
import com.neouul.umc10android.week09.domain.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WishRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource
) : WishRepository {

    override fun getWishedProductsFlow(): Flow<List<Product>> {
        return productDataSource.getWishedProducts().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun getWishedProducts(): List<Product> {
        return getWishedProductsFlow().first()
    }

    override suspend fun addWishedProduct(product: Product) {
        productDataSource.updateWishStatus(product.id, true)
    }

    override suspend fun removeWishedProduct(product: Product) {
        productDataSource.updateWishStatus(product.id, false)
    }
}
