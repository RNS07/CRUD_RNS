package com.rns.accomium.features.product.productAdd.presentation

import androidx.compose.runtime.MutableState
import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviIntent
import com.rns.accomium.data.product.model.Product

sealed class AddProductIntent: MviIntent {

     data class AddProduct(val product: Product) : AddProductIntent()
     data class ProductNameChange(val productName:String): AddProductIntent()

}