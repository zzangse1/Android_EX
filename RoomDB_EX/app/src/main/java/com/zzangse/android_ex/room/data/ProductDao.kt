package com.zzangse.android_ex.room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg product: Product)

    @Update
    suspend fun update(vararg product: Product)

    @Delete
    suspend fun delete(vararg product: Product)

    @Query("SELECT * FROM product ")
    suspend fun loadAllProducts(): List<Product>

    @Query("SELECT * FROM product WHERE price > :minPrice")
    suspend fun loadAllProductOverPrice(minPrice: Int): List<Product>
}