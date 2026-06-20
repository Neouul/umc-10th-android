package com.neouul.umc10android.week10.data.data_source.remote

import com.neouul.umc10android.week10.data.data_source.remote.api.ProductService
import com.neouul.umc10android.week10.domain.model.Product
import javax.inject.Inject

class RemoteProductDataSourceImpl @Inject constructor(
    private val productService: ProductService
) : RemoteProductDataSource {
    override suspend fun getProducts(): List<Product> {
        val response = productService.getProducts()
        return if (response.isSuccessful) {
            response.body()?.products?.map { it.toDomain() } ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getHomeProducts(): List<Product> {
        val response = productService.getHomeProducts()
        return if (response.isSuccessful) {
            response.body()?.products?.map { it.toDomain() } ?: emptyList()
        } else {
            emptyList()
        }
    }
}
