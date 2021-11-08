package com.rns.accomium.features.product.productList.domain.usecase

import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.model.Product
import com.rns.accomium.features.product.productList.domain.repository.ListProductRepository
import javax.inject.Inject

class ListProductUsecase @Inject constructor(
    public val listProductRepository: ListProductRepository
) {
    operator fun invoke(): List<Product> {
        return listProductRepository.fetchList()
    }
}