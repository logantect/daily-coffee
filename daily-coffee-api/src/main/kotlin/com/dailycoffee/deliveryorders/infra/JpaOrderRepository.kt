package com.dailycoffee.deliveryorders.infra

import com.dailycoffee.deliveryorders.domain.Order
import com.dailycoffee.deliveryorders.domain.OrderRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface JpaOrderRepository : OrderRepository, JpaRepository<Order, UUID>
