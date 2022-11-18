package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.support.utils.IdGenerator
import java.time.ZonedDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @Id @Column(columnDefinition = "BINARY(16)") val id: UUID,
    status: OrderStatus,
    @Column(nullable = false) val orderDateTime: ZonedDateTime,
    @Embedded val deliveryAddress: DeliveryAddress,
    @Embedded val orderLineItems: OrderLineItems,
) {
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    var status: OrderStatus = status
        protected set

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

    fun place(orderValidator: OrderValidator) {
        orderValidator.validate(this)
    }

    fun accept() {
        require(status.isWaiting())
        this.status = OrderStatus.ACCEPTED
    }

    fun serve() {
        require(status.isAccepted())
        this.status = OrderStatus.SERVED
    }

    fun startDelivery() {
        require(status.isServed())
        this.status = OrderStatus.DELIVERING
    }

    fun completeDelivery() {
        require(status.isDelivering())
        this.status = OrderStatus.DELIVERED
    }

    fun complete() {
        require(status.isDelivered())
        this.status = OrderStatus.COMPLETED
    }

    fun getOrderLineItems(): List<OrderLineItem> {
        return orderLineItems.orderLineItems
    }
}
