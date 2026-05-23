package com.neouul.umc10android.week08.data.dto

import com.google.gson.annotations.SerializedName
import com.neouul.umc10android.week08.domain.model.Product

data class ProductResponseDto(
    @SerializedName("products") val products: List<ProductDto>
)

data class ProductDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("detailDescription") val detailDescription: String,
    @SerializedName("category") val category: String,
    @SerializedName("colorNumber") val colorNumber: Int,
    @SerializedName("price") val price: String,
    @SerializedName("img") val img: String,
    @SerializedName("isBestSeller") val isBestSeller: Boolean = false,
    @SerializedName("isWished") val isWished: Boolean = false,
    @SerializedName("isHome") val isHome: Boolean = false
) {
    fun toDomain(): Product = Product(
        id = id,
        name = name,
        description = description,
        detailDescription = detailDescription,
        category = category,
        colorNumber = colorNumber,
        price = price,
        img = img,
        isWished = isWished,
        isBestSeller = isBestSeller
    )
}
