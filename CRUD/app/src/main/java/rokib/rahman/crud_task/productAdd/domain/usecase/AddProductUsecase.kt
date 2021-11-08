package com.rns.accomium.features.product.productAdd.domain.usecase

import com.rns.accomium.data.customer.model.Customer
import com.rns.accomium.data.product.model.Product
import com.rns.accomium.features.customer.customerAdd.domain.repository.AddCustomerRepository
import com.rns.accomium.features.product.productAdd.domain.repository.AddProductRepository
import javax.inject.Inject

class AddProductUsecase  @Inject constructor(
    public val addProductRepository: AddProductRepository
) {
    operator fun invoke(product: Product)= addProductRepository.insertProduct(product)

}