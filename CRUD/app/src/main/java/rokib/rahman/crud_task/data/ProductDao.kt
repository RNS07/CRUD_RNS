package com.rns.accomium.data.product.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.model.Product


@Dao
interface ProductDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(product: Product)
/*
    @Query("SELECT * FROM post WHERE id = :postId")
    fun load(postId: String): Post
*/

    @Query("SELECT * FROM product ")
    fun loadAll(): List<Product>
}