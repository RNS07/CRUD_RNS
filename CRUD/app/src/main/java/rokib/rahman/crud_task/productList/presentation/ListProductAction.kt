package com.rns.accomium.features.product.productList.presentation

import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviAction
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerAction

sealed class ListProductAction: MviAction {
    object LoadAllActions : ListProductAction()
    object NoAllActions : ListProductAction()
}