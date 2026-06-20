package com.neouul.umc10android.week10.data.data_source.remote

import com.neouul.umc10android.week10.domain.model.Product

interface RemoteProductDataSource {
    suspend fun getProducts(): List<Product>
    suspend fun getHomeProducts(): List<Product>
}
