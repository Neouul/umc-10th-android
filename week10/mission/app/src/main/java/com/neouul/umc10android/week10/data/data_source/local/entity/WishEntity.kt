package com.neouul.umc10android.week10.data.data_source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish_status")
data class WishEntity(
    @PrimaryKey val productId: Long,
    val isWished: Boolean
)
