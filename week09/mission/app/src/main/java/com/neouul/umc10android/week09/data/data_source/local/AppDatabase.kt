package com.neouul.umc10android.week09.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.neouul.umc10android.week09.data.data_source.local.dao.ProductDao
import com.neouul.umc10android.week09.data.data_source.local.entity.ProductEntity
import com.neouul.umc10android.week09.data.data_source.local.entity.WishEntity

@Database(entities = [ProductEntity::class, WishEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
