package com.neouul.umc10android.week09.data.data_source.local.entity

// ProductEntity와 WishEntity의 Join 결과를 담기 위한 POJO 클래스
data class ProductWithWish(
    val id: Long,
    val name: String,
    val description: String,
    val detailDescription: String,
    val category: String,
    val colorNumber: Int,
    val price: String,
    val img: String,
    val isBestSeller: Boolean,
    val isWished: Boolean
)
