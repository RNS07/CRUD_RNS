package com.rns.accomium.features.product.productList.presentation

import com.rns.accomium.AccomiumMainBase.presentation.mvibase.MviIntent
import com.rns.accomium.features.customer.customerList.presentation.ListCustomerIntent

sealed class ListProductIntent: MviIntent {
    object LoadAllIntent: ListProductIntent()
    object RefreshIntent: ListProductIntent()
}