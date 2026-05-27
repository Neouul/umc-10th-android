package com.neouul.umc10android.week09.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.neouul.umc10android.week09.data.data_source.local.entity.ProductEntity
import com.neouul.umc10android.week09.data.data_source.local.entity.ProductWithWish
import com.neouul.umc10android.week09.data.data_source.local.entity.WishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("""
        SELECT p.*, IFNULL(w.isWished, 0) as isWished 
        FROM products p 
        LEFT JOIN wish_status w ON p.id = w.productId
    """)
    fun getAllProductsWithWish(): Flow<List<ProductWithWish>>

    @Query("""
        SELECT p.*, IFNULL(w.isWished, 0) as isWished 
        FROM products p 
        LEFT JOIN wish_status w ON p.id = w.productId
        WHERE p.id IN (:ids)
    """)
    fun getProductsByIds(ids: List<Long>): Flow<List<ProductWithWish>>

    @Query("""
        SELECT p.*, IFNULL(w.isWished, 0) as isWished 
        FROM products p 
        INNER JOIN wish_status w ON p.id = w.productId
        WHERE w.isWished = 1
    """)
    fun getWishedProducts(): Flow<List<ProductWithWish>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWishStatus(wish: WishEntity)

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductCount(): Int
}
