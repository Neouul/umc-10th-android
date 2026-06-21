package com.neouul.umc10android.week10.data.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String,
    val detailDescription: String,
    val category: String,
    val colorNumber: Int,
    val price: String,
    val img: String,
    val isBestSeller: Boolean
)
