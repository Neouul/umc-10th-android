package com.neouul.umc10android.week08.data.data_source

import com.neouul.umc10android.week08.data.data_source.local.dao.ProductDao
import com.neouul.umc10android.week08.data.data_source.local.entity.ProductWithWish
import com.neouul.umc10android.week08.data.data_source.local.entity.ProductEntity
import com.neouul.umc10android.week08.data.data_source.local.entity.WishEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDataSourceImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductDataSource {

    override fun getAllProducts(): Flow<List<ProductWithWish>> {
        return productDao.getAllProductsWithWish()
    }

    override fun getProductsByIds(ids: List<Long>): Flow<List<ProductWithWish>> {
        return productDao.getProductsByIds(ids)
    }

    override fun getWishedProducts(): Flow<List<ProductWithWish>> {
        return productDao.getWishedProducts()
    }

    override suspend fun insertProducts(products: List<ProductEntity>) {
        productDao.insertProducts(products)
    }

    override suspend fun updateWishStatus(productId: Long, isWished: Boolean) {
        productDao.updateWishStatus(WishEntity(productId, isWished))
    }

    override suspend fun getProductCount(): Int {
        return productDao.getProductCount()
    }
}
