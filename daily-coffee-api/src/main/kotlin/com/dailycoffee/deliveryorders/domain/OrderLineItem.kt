package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.support.utils.IdGenerator
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "order_items")
class OrderLineItem(
    @Id @Column(columnDefinition = "BINARY(16)") val id: UUID,
    @Column(columnDefinition = "BINARY(16)") val menuId: UUID,
    @Embedded val price: Price,
    @Embedded val quantity: Quantity
) {
    constructor(menuId: UUID, price: BigDecimal, quantity: Long) : this(
        IdGenerator.createId(),
        menuId,
        Price(price),
        Quantity(quantity)
    )
}
