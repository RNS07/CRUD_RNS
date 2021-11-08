package com.rns.accomium.features.product.productAdd.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviViewState
import com.rns.accomium.AccomiumMainBase.domain.model.ListData

data class AddProductViewState(
    val isLoading: Boolean,
    val data: Boolean,
    var producatandServiceName: MutableState<String> = mutableStateOf(""),
    var producatandServiceCode: MutableState<String> = mutableStateOf(""),
    var measurementUnit : MutableState<String> = mutableStateOf(""),
    var saleRate : MutableState<String> = mutableStateOf(""),
    var purchaseRate : MutableState<String> = mutableStateOf(""),
    var description : MutableState<String> = mutableStateOf(""),
    var error: String?
) : MviViewState {


    companion object {
        fun init(): AddProductViewState {
            return AddProductViewState(
                false,
                false,
                mutableStateOf("") ,
                mutableStateOf("") ,
                mutableStateOf("") ,
                mutableStateOf("") ,
                mutableStateOf("") ,
                mutableStateOf("") ,
                null
            )
        }
    }

    fun reduce(
        previousState: AddProductViewState,
        result: AddProductResult
    ): AddProductViewState {
        return when (result) {
            is AddProductResult.SuccessResult -> previousState.copy(
                isLoading = false,
                data = result.options,
                error = null
            )
            is AddProductResult.Error -> previousState.copy(error = "Error")
            is AddProductResult.LoadAllResult -> previousState.copy(isLoading = true, error = null)
            is AddProductResult.ProductNameChange -> previousState.copy(producatandServiceName = mutableStateOf("${result.productName}"))
        }
    }


}