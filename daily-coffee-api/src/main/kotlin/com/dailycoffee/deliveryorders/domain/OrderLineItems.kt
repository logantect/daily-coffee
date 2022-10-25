package com.dailycoffee.deliveryorders.domain

class OrderLineItems(
    private var _orderLineItems: MutableList<OrderLineItem>
) {
    val orderLineItems: List<OrderLineItem>
        get() = _orderLineItems.toList()
}
