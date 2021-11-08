package com.rns.accomium.features.product.productAdd.presentation

import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviResult
import com.rns.accomium.AccomiumMainBase.domain.model.ListData
import com.rns.accomium.features.customer.customerAdd.presentation.AddCustomerResult

sealed class AddProductResult: MviResult {

    object LoadAllResult: AddProductResult()
    class SuccessResult(val options: Boolean): AddProductResult()
    object  Error : AddProductResult()
    //object EmptyResult: Result()
    class ProductNameChange(val productName:String):AddProductResult()

}