package com.dailycoffee.deliveryorders.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class DeliveryAddress(
    @Column(nullable = false) val address: String,
) {
    init {
        require(address.isNotBlank())
    }
}
