package com.dailycoffee.deliveryorders.application

import com.dailycoffee.deliveryorders.domain.Order
import com.dailycoffee.deliveryorders.domain.OrderLineItem
import com.dailycoffee.deliveryorders.domain.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    fun create(command: OrderRequest): OrderResponse {
        val order = orderRepository.save(
            Order(
                deliveryAddress = command.deliveryAddress,
                orderLineItems = command.orderLineItems.map {
                    OrderLineItem(
                        menuId = it.menuId,
                        price = it.price,
                        quantity = it.quantity
                    )
                }
            )
        )
        return OrderResponse(order)
    }
}
