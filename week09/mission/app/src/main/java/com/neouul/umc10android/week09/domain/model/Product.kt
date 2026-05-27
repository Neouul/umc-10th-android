package com.neouul.umc10android.week09.domain.model

data class Product(
    val id: Long,
    val name: String,
    val description: String = "",
    val detailDescription: String = "",
    val category: String = "",
    val colorNumber: Int = 0,
    val price: String = "",
    val img: String = "",
    val isWished: Boolean = false,
    val isBestSeller: Boolean = false,
)
