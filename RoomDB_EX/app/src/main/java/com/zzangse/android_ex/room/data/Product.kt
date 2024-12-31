package com.zzangse.android_ex.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    val label: String,
    val price: Int,
    @ColumnInfo(name = "brand_name") val brandName: String
)
