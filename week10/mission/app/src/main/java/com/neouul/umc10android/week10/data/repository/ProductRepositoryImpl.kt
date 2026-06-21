package com.neouul.umc10android.week10.data.repository

import com.neouul.umc10android.week10.data.data_source.ProductDataSource
import com.neouul.umc10android.week10.data.data_source.remote.RemoteProductDataSource
import com.neouul.umc10android.week10.data.mapper.toDomain
import com.neouul.umc10android.week10.data.mapper.toEntity
import com.neouul.umc10android.week10.domain.model.Product
import com.neouul.umc10android.week10.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val localDataSource: ProductDataSource,
    private val remoteDataSource: RemoteProductDataSource
) : ProductRepository {

    override fun getAllProducts(): Flow<List<Product>> {
        return localDataSource.getAllProducts().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getHomeProducts(): Flow<List<Product>> {
        // 홈 화면에 보여줄 특정 상품들 (ID: 3, 5, 6)
        return localDataSource.getProductsByIds(listOf(3L, 5L, 6L)).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getProduct(productId: Long): Flow<Product?> {
        return localDataSource.getProductsByIds(listOf(productId)).map { list ->
            list.firstOrNull()?.toDomain()
        }
    }

    override suspend fun syncProducts() {
        try {
            // 로컬에 데이터가 없을 때만 서버와 동기화 (필요에 따라 정책 변경 가능)
            if (localDataSource.getProductCount() == 0) {
                val remoteProducts = remoteDataSource.getProducts()
                if (remoteProducts.isNotEmpty()) {
                    localDataSource.insertProducts(remoteProducts.map { it.toEntity() })
                    
                    // 초기 찜 상태 설정
                    remoteProducts.forEach {
                        localDataSource.updateWishStatus(it.id, false)
                    }
                }
            }
        } catch (e: Exception) {
            // 에러 로깅
        }
    }

    override suspend fun updateProduct(product: Product) {
        localDataSource.insertProducts(listOf(product.toEntity()))
        localDataSource.updateWishStatus(product.id, product.isWished)
    }

    override suspend fun updateWishStatus(productId: Long, isWished: Boolean) {
        localDataSource.updateWishStatus(productId, isWished)
    }
}
