package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.utils.IdGenerator
import java.time.ZonedDateTime
import java.util.UUID

class Order(
    val id: UUID,
    val status: OrderStatus,
    val orderDateTime: ZonedDateTime,
    val deliveryAddress: String,
    val orderLineItems: OrderLineItems,
) {
    constructor(
        deliveryAddress: String,
        orderLineItems: List<OrderLineItem>
    ) : this(
        IdGenerator.createId(),
        OrderStatus.WAITING,
        ZonedDateTime.now(),
        deliveryAddress,
        OrderLineItems(orderLineItems.toMutableList())
    )

    constructor(
        status: OrderStatus,
        deliveryAddress: String,
        orderLineItems: List<OrderLineItem>,
    ) : this(
        IdGenerator.createId(),
        status,
        ZonedDateTime.now(),
        deliveryAddress,
        OrderLineItems(orderLineItems.toMutableList())
    )
}
