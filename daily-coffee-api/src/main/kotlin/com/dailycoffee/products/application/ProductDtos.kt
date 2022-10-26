package com.dailycoffee.products.application

import com.dailycoffee.products.domain.Product
import java.math.BigDecimal
import java.util.UUID

data class CreateProductRequest(
    val name: String,
    val price: BigDecimal
)

data class ProductResponse(
    val id: UUID,
    val name: String,
    val price: BigDecimal
) {
    constructor(product: Product) : this(
        id = product.id,
        name = product.name.name,
        price = product.price.amount,
    )
}
