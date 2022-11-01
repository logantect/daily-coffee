package com.dailycoffee.deliveryorders.application

import com.dailycoffee.deliveryorders.domain.Order
import com.dailycoffee.deliveryorders.domain.OrderLineItem
import com.dailycoffee.deliveryorders.domain.OrderStatus
import java.math.BigDecimal
import java.time.ZonedDateTime
import java.util.UUID

data class OrderRequest(
    val deliveryAddress: String,
    val orderLineItems: List<OrderLineItemsRequest>,
)

data class OrderLineItemsRequest(
    val menuId: UUID,
    val price: BigDecimal,
    val quantity: Long
)

data class OrderResponse(
    val id: UUID,
    val status: OrderStatus,
    val orderDateTime: ZonedDateTime,
    val deliveryAddress: String,
    val orderLineItems: List<OrderLineItemsResponse>,
) {
    constructor(order: Order) : this(
        id = order.id,
        status = order.status,
        orderDateTime = order.orderDateTime,
        deliveryAddress = order.deliveryAddress.address,
        orderLineItems = order.orderLineItems.orderLineItems.map {
            OrderLineItemsResponse(it)
        }

    )
}

data class OrderLineItemsResponse(
    val id: UUID,
    val menuId: UUID,
    val price: BigDecimal,
    val quantity: Long
) {
    constructor(orderLineItem: OrderLineItem) : this(
        id = orderLineItem.id,
        menuId = orderLineItem.menuId,
        price = orderLineItem.price.amount,
        quantity = orderLineItem.quantity.quantity,
    )
}
