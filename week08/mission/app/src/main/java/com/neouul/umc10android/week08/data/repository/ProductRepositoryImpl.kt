package com.neouul.umc10android.week08.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.neouul.umc10android.week08.data.data_source.ProductDataSource
import com.neouul.umc10android.week08.domain.model.Product
import com.neouul.umc10android.week08.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDataSource: ProductDataSource,
    private val gson: Gson,
) : ProductRepository {

    private val productListType = object : TypeToken<List<Product>>() {}.type

    override fun getTotalProducts(): Flow<List<Product>> {
        return productDataSource.getTotalProducts().map { json ->
            try {
                if (json == "[]" || json.isEmpty()) emptyList()
                else gson.fromJson(json, productListType) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    override fun getHomeProducts(): Flow<List<Product>> {
        return productDataSource.getHomeProducts().map { json ->
            try {
                if (json == "[]" || json.isEmpty()) emptyList()
                else gson.fromJson(json, productListType) ?: emptyList()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    override suspend fun initializeDataIfNeeded() {
        val currentTotalJson = productDataSource.getTotalProducts().first()
        if (currentTotalJson == "[]" || currentTotalJson.isEmpty()) {
            val totalJson = gson.toJson(mockProducts)
            productDataSource.updateTotalProduct(totalJson)

            val homeProducts = mockProducts.filter { it.id in listOf(3L, 5L, 6L) }
            productDataSource.updateHomeProduct(gson.toJson(homeProducts))
        }
    }

    override suspend fun updateTotalProduct(product: Product) {
        val currentProducts = getTotalProducts().first().toMutableList()
        val index = currentProducts.indexOfFirst { it.id == product.id }
        if (index != -1) {
            currentProducts[index] = product
            productDataSource.updateTotalProduct(gson.toJson(currentProducts))

            // 홈 데이터 동기화
            val homeProducts = getHomeProducts().first().toMutableList()
            val homeIndex = homeProducts.indexOfFirst { it.id == product.id }
            if (homeIndex != -1) {
                homeProducts[homeIndex] = product
                productDataSource.updateHomeProduct(gson.toJson(homeProducts))
            }
        }
    }

    override suspend fun updateHomeProduct(product: Product) {
        val currentProducts = getHomeProducts().first().toMutableList()
        val index = currentProducts.indexOfFirst { it.id == product.id }
        if (index != -1) {
            currentProducts[index] = product
            productDataSource.updateHomeProduct(gson.toJson(currentProducts))
        }
    }

    private val mockProducts = listOf(
        Product(
            id = 1L,
            name = "Nike Everyday Plus Cushioned",
            description = "Crew Socks (6 Pairs)",
            detailDescription = "Extra cushioning under the heel and forefoot.",
            category = "Training & Library",
            colorNumber = 1,
            price = "US\$22",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/76a91f58-00a8-477c-a458-197e42d76816/everyday-plus-cushioned-training-crew-socks-6-pairs-9D6v6v.png",
            isBestSeller = true
        ),
        Product(
            id = 2L,
            name = "Nike Air Max 270",
            description = "Men's Shoes",
            detailDescription = "The first lifestyle Air Max brings you style, comfort and big attitude.",
            category = "Lifestyle",
            colorNumber = 3,
            price = "US\$160",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/awwp9bhsqy0dt9gh966n/air-max-270-mens-shoes-Kpj94z.png"
        ),
        Product(
            id = 3L,
            name = "Nike Air Force 1 '07",
            description = "Men's Shoes",
            detailDescription = "The radiance lives on in the Nike Air Force 1 '07.",
            category = "Lifestyle",
            colorNumber = 2,
            price = "US\$115",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/b7d9211c-26e7-431a-ac24-b0540fb3c00f/air-force-1-07-mens-shoes-jps0P8.png"
        ),
        Product(
            id = 4L,
            name = "Nike Dunk Low Retro",
            description = "Men's Shoes",
            detailDescription = "Created for the hardwood but taken to the streets.",
            category = "Lifestyle",
            colorNumber = 5,
            price = "US\$115",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/g16y8g0i70f0f0f0f0f0/dunk-low-retro-mens-shoes-76Kn9F.png"
        ),
        Product(
            id = 5L,
            name = "Nike Pegasus 40",
            description = "Men's Road Running Shoes",
            detailDescription = "A springy ride for every run.",
            category = "Running",
            colorNumber = 7,
            price = "US\$130",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/e66271e1-e170-4cc8-9441-26c92d50e80e/pegasus-40-mens-road-running-shoes-MCZ9Hz.png"
        ),
        Product(
            id = 6L,
            name = "Nike Blazer Mid '77 Vintage",
            description = "Men's Shoes",
            detailDescription = "Vintage style, modern comfort.",
            category = "Lifestyle",
            colorNumber = 2,
            price = "US\$105",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/60451e06-5f73-45f8-842e-9d261e47f526/blazer-mid-77-vintage-mens-shoes-8N6R5v.png"
        )
    )
}
