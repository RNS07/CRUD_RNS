package com.rns.accomium.features.product.productAdd.domain.repository

import android.util.Log
import com.rns.accomium.data.customer.data.CustomerDao
import com.rns.accomium.data.product.data.ProductDao
import com.rns.accomium.data.product.model.Product
import java.lang.Exception
import javax.inject.Inject

class AddProductRepository @Inject constructor(
    public val ProductDao: ProductDao
) {

    fun insertProduct(product: Product):Boolean{

        return insetInDatabase(product)
    }

    private fun insetInDatabase(product: Product):Boolean {


        try{

            ProductDao.save(product)
            Log.d("AddProductRepository", "$product")

            return true
        }catch (e: Exception){
            Log.d("AddProductRepositerror", "$e")

            return false
        }
    }
}