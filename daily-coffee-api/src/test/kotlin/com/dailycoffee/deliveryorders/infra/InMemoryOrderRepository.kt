package com.dailycoffee.deliveryorders.infra

import com.dailycoffee.deliveryorders.domain.Order
import com.dailycoffee.deliveryorders.domain.OrderRepository
import java.util.Optional
import java.util.UUID

class InMemoryOrderRepository : OrderRepository {
    override fun save(order: Order): Order {
        storage[order.id] = order
        return order
    }

    override fun findById(id: UUID): Optional<Order> {
        return Optional.ofNullable(storage[id])
    }

    override fun findAll(): List<Order> {
        return storage.values.toList()
    }

    override fun deleteAll() {
        storage.clear()
    }

    companion object {
        private val storage: MutableMap<UUID, Order> = HashMap()
    }
}
