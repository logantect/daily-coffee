package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.utils.IdGenerator
import java.time.ZonedDateTime
import java.util.UUID

class Order(
    val id: UUID,
    val status: OrderStatus,
    val orderDateTime: ZonedDateTime,
    val orderLineItems: OrderLineItems,
) {
    constructor(
        orderLineItems: List<OrderLineItem>
    ) : this(
        IdGenerator.createId(),
        OrderStatus.WAITING,
        ZonedDateTime.now(),
        OrderLineItems(orderLineItems.toMutableList())
    )

    constructor(
        status: OrderStatus,
        orderLineItems: List<OrderLineItem>
    ) : this(
        IdGenerator.createId(),
        status,
        ZonedDateTime.now(),
        OrderLineItems(orderLineItems.toMutableList())
    )
}
