package com.zzangse.android_ex.room.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}