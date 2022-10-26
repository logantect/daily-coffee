package com.dailycoffee.products.application

import com.dailycoffee.products.domain.DisplayedName
import com.dailycoffee.products.domain.Price
import com.dailycoffee.products.domain.Product
import java.math.BigDecimal
import java.util.UUID

data class CreateProductRequest(
    val name: String,
    val price: BigDecimal
)

data class ProductResponse(
    val id: UUID,
    val name: DisplayedName,
    val price: Price
) {
    constructor(product: Product) : this(
        id = product.id,
        name = product.name,
        price = product.price,
    )
}
