package com.dailycoffee

import com.dailycoffee.deliveryorders.application.OrderLineItemsRequest
import com.dailycoffee.deliveryorders.application.OrderRequest
import com.dailycoffee.deliveryorders.domain.Order
import com.dailycoffee.deliveryorders.domain.OrderLineItem
import com.dailycoffee.deliveryorders.domain.OrderStatus
import java.math.BigDecimal
import java.util.UUID

fun order(orderLineItems: List<OrderLineItem>): Order {
    return order("서울특별시 강남구 논현로 656", orderLineItems)
}

fun order(deliveryAddress: String, orderLineItems: List<OrderLineItem>): Order {
    return Order(deliveryAddress, orderLineItems)
}

fun order(status: OrderStatus, orderLineItems: List<OrderLineItem>): Order {
    return order(status, "서울특별시 강남구 논현로 656", orderLineItems)
}

fun order(status: OrderStatus, deliveryAddress: String, orderLineItems: List<OrderLineItem>): Order {
    return Order(status, deliveryAddress, orderLineItems)
}

fun orderLineItem(menuId: UUID, price: BigDecimal, quantity: Long): OrderLineItem {
    return OrderLineItem(menuId, price, quantity)
}

fun orderRequest(deliveryAddress: String, orderLineItems: List<OrderLineItemsRequest>) = OrderRequest(
    deliveryAddress = deliveryAddress,
    orderLineItems = orderLineItems
)

fun orderLineItemsRequest(menuId: UUID, price: BigDecimal, quantity: Long) = OrderLineItemsRequest(
    menuId = menuId,
    price = price,
    quantity = quantity
)
