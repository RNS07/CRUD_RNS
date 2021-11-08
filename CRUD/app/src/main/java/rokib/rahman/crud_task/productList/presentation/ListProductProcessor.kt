package com.rns.accomium.features.product.productList.presentation

import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviProcessor
import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.model.Product
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerAction
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerResult
import com.rns.accomium.features.product.productList.domain.usecase.ListProductUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ListProductProcessor @Inject constructor(
    val listProductUsecase: ListProductUsecase,
): MviProcessor<ListProductAction, ListProductResult> {

    override fun process(action: ListProductAction): Flow<ListProductResult> {
        return when(action){
            is ListProductAction.LoadAllActions -> loadResult()
            else -> TODO()
        }
    }
    fun loadResult():  Flow<ListProductResult>{
        return flow {
            emit(listProductUsecase())
        }.map { options ->
            defineResult(options)
        }
            .onStart {
                emit(ListProductResult.LoadAllResult)
            }
            .catch { error ->
                emit( ListProductResult.Error(error))
            }.flowOn(Dispatchers.IO)
    }
    public fun defineResult(options: List<Product>): ListProductResult {
        if(!options.isEmpty()) return ListProductResult.SuccessResult((options))
        else return  ListProductResult.Empty

    }


}