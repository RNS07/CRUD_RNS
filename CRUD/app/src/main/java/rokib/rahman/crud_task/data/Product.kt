package com.rns.accomium.data.product.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
data class Product(
    @PrimaryKey(autoGenerate = true)var id: Int =0,
    var productServicename:String ="",
    var productServiceCode:String ="",
    var measurementUnit:String ="",
    var saleRate:String ="",
    var purchaseRate:String ="",
    var description:String ="",
    var userId:String ="",
)