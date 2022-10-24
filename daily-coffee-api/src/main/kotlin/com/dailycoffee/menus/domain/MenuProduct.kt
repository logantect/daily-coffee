package com.dailycoffee.menus.domain

import com.dailycoffee.utils.IdGenerator
import java.math.BigDecimal
import java.util.UUID

class MenuProduct(
    val id: UUID,
    val productId: UUID,
    val price: BigDecimal,
    val quantity: Long,
) {

    constructor(productId: UUID, price: BigDecimal, quantity: Long) : this(
        IdGenerator.createId(),
        productId,
        price,
        quantity,
    )
}
