package com.neouul.umc10android.week09.data.data_source.remote.api

import com.neouul.umc10android.week09.data.dto.ProductResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("Nike/nike_products.json")
    suspend fun getProducts(): Response<ProductResponseDto>

    @GET("Nike/nike_products.json")
    suspend fun getHomeProducts(): Response<ProductResponseDto>
}
