package com.neouul.umc10android.week08.data.data_source.remote

import com.neouul.umc10android.week08.domain.model.Product

interface RemoteProductDataSource {
    suspend fun getProducts(): List<Product>
    suspend fun getHomeProducts(): List<Product>
}
