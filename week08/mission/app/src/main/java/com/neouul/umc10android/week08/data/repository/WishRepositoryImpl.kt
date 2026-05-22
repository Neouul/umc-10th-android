package com.neouul.umc10android.week08.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.neouul.umc10android.week08.data.data_source.ProductDataSource
import com.neouul.umc10android.week08.domain.model.Product
import com.neouul.umc10android.week08.domain.repository.WishRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WishRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
    private val gson: Gson,
) : WishRepository {

    private val productListType = object : TypeToken<List<Product>>() {}.type

    override fun getWishedProductsFlow(): Flow<List<Product>> {
        return productDataSource.getWishProducts().map { json ->
            gson.fromJson(json, productListType) ?: emptyList()
        }
    }

    override suspend fun getWishedProducts(): List<Product> {
        return getWishedProductsFlow().first()
    }

    override suspend fun addWishedProduct(product: Product) {
        val currentWished = getWishedProducts().toMutableList()
        // 이미 위시리스트에 있는 경우 중복 추가 방지
        if (currentWished.none { it.id == product.id }) {
            currentWished.add(product.copy(isWished = true))
            val json = gson.toJson(currentWished)
            productDataSource.updateWishProduct(json)
        }
    }

    override suspend fun removeWishedProduct(product: Product) {
        val currentWished = getWishedProducts().toMutableList()
        // id가 일치하는 상품 제거
        val removed = currentWished.removeIf { it.id == product.id }
        if (removed) {
            val json = gson.toJson(currentWished)
            productDataSource.updateWishProduct(json)
        }
    }
}
