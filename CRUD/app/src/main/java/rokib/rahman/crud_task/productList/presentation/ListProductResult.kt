package com.rns.accomium.features.product.productList.presentation

import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviResult
import com.rns.accomium.AccomiumMainBase.domain.model.ListData
import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.model.Product
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerResult

sealed class ListProductResult: MviResult {
    object LoadAllResult: ListProductResult()
    class SuccessResult(val listProductResult: List<Product>): ListProductResult()
    data class Error(val error: Throwable) : ListProductResult()
    object Empty: ListProductResult()

}