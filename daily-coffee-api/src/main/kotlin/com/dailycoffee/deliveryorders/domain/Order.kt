package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.utils.IdGenerator
import java.time.ZonedDateTime
import java.util.UUID

class Order(
    val id: UUID,
    status: OrderStatus,
    val orderDateTime: ZonedDateTime,
    val deliveryAddress: DeliveryAddress,
    val orderLineItems: OrderLineItems,
) {
    var status: OrderStatus = status
        private set

    constructor(
        deliveryAddress: String,
        orderLineItems: List<OrderLineItem>
    ) : this(
        IdGenerator.createId(),
        OrderStatus.WAITING,
        ZonedDateTime.now(),
        DeliveryAddress(deliveryAddress),
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
        DeliveryAddress(deliveryAddress),
        OrderLineItems(orderLineItems.toMutableList())
    )

    fun accept() {
        require(status.isWaiting())
        this.status = OrderStatus.ACCEPTED
    }

    fun serve() {
        require(status.isAccepted())
        this.status = OrderStatus.SERVED
    }
}
