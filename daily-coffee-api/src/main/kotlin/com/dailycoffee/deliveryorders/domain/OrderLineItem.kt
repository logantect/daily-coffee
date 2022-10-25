package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.utils.IdGenerator
import java.math.BigDecimal
import java.util.UUID

class OrderLineItem(
    val id: UUID,
    val menuId: UUID,
    val price: BigDecimal,
    val quantity: Long
) {

    constructor(menuId: UUID, price: BigDecimal, quantity: Long) : this(IdGenerator.createId(), menuId, price, quantity)
}
