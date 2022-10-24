package com.dailycoffee.menus.domain

import com.dailycoffee.utils.IdGenerator
import java.math.BigDecimal
import java.util.UUID

class MenuProduct(
    val id: UUID,
    val productId: UUID,
    val price: Price,
    val quantity: Quantity,
) {

    constructor(productId: UUID, price: BigDecimal, quantity: Long) : this(
        IdGenerator.createId(),
        productId,
        Price(price),
        Quantity(quantity),
    )
}
