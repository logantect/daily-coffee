package com.dailycoffee.deliveryorders.domain

import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Embeddable
class OrderLineItems(
    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER,
        orphanRemoval = true,
    )
    @JoinColumn(
        name = "order_id", nullable = false, updatable = false,
        foreignKey = ForeignKey(name = "fk_order_item_order_id_ref_order_id")
    )
    private var _orderLineItems: MutableList<OrderLineItem>
) {
    val orderLineItems: List<OrderLineItem>
        get() = _orderLineItems.toList()
}
