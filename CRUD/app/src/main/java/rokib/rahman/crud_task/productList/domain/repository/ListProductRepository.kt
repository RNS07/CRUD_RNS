package com.rns.accomium.features.product.productList.domain.repository

import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.data.ProductDao
import com.rns.accomium.data.product.model.Product
import javax.inject.Inject

class ListProductRepository @Inject constructor(
    public val productDao: ProductDao
)  {

    fun fetchList():List<Product>{
        return getProductList()
    }

    private  fun getProductList() :List<Product> {
        return productDao.loadAll()

    }
}