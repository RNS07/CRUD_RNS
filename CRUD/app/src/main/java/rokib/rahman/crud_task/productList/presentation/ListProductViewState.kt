package com.rns.accomium.features.product.productList.presentation

import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviViewState
import com.rns.accomium.AccomiumMainBase.domain.model.ListData
import com.rns.accomium.data.product.model.Product

data class ListProductViewState(
    val isLoading: Boolean,
    val data: List<Product> = mutableListOf(),
    val error: Throwable?,
    val empty: Boolean
) : MviViewState {


    companion object {
        fun init(): ListProductViewState {
            return ListProductViewState(
                false,
                mutableListOf(),
                null,
                false
            )
        }
    }

    fun reduce(
        previousState: ListProductViewState,
        result: ListProductResult
    ): ListProductViewState {
        return when (result) {
            is ListProductResult.SuccessResult -> previousState.copy(
                isLoading = false,
                data = result.listProductResult,
                error = null
            )
            is ListProductResult.Error -> previousState.copy(error = result.error)
            is ListProductResult.LoadAllResult -> previousState.copy(isLoading = true, error = null)
            is ListProductResult.Empty -> previousState.copy(empty = true)
        }
    }


}