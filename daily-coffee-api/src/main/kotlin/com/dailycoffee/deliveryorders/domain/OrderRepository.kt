package com.dailycoffee.deliveryorders.domain

import java.util.Optional
import java.util.UUID

interface OrderRepository {
    fun save(order: Order): Order

    fun findById(id: UUID): Optional<Order>

    fun findAll(): List<Order>
    fun deleteAll()
}
