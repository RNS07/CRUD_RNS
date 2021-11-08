package com.rns.accomium.features.product.productAdd.presentation

import android.util.Log
import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviProcessor
import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.model.Product
import com.rns.accomium.features.customer.customerAdd.presentation.AddCustomerAction
import com.rns.accomium.features.customer.customerAdd.presentation.AddCustomerResult
import com.rns.accomium.features.product.productAdd.domain.usecase.AddProductUsecase
import com.rns.accomium.features.product.productAdd.presentation.ui.AddProduct
import com.rns.accomium.features.product.productAdd.presentation.ui.cAddProduct
import com.rns.accomium.features.sale.saleAdd.presentation.AddSaleResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AddProductProcessor @Inject constructor(
    val addProductUsecase: AddProductUsecase
): MviProcessor<AddProductAction, AddProductResult> {

    override fun process(action: AddProductAction): Flow<AddProductResult> {
        return when(action){
            is AddProductAction.AddProduct -> addProduct(action.product)
            is AddProductAction.ProductNameChange -> flow{
                Log.d("AddProductprocessIntent", "ProductNameChange")
                emit(action.productName)
            }.map { list ->
                AddProductResult.ProductNameChange((list))
            }.catch { error ->
                Log.d("onClickListChangeerr","${error}")
            }

        }
    }

    fun addProduct(product : Product): Flow<AddProductResult>{
        return flow {
            emit(addProductUsecase(product))
        }.map { options ->
            defineResult(options)
        }
            .onStart {
                emit(AddProductResult.LoadAllResult)
            }
            .catch { error ->
                emit( AddProductResult.Error)
            }.flowOn(Dispatchers.IO)
    }

    public fun defineResult(options: Boolean): AddProductResult {

        return when (options) {
            true -> AddProductResult.SuccessResult((options))
            else -> AddProductResult.Error
        }
    }

}