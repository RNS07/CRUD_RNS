package com.rns.accomium.features.product.productAdd.presentation

import androidx.compose.runtime.MutableState
import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviAction
import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.model.Product
import com.rns.accomium.features.customer.customerAdd.presentation.AddCustomerAction

sealed class AddProductAction: MviAction {
    data class AddProduct(val product: Product) : AddProductAction()
    data class ProductNameChange(val productName: String): AddProductAction()

}