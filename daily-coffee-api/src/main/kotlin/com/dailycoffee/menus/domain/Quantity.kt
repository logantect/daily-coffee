package com.dailycoffee.menus.domain

import javax.persistence.Column
import javax.persistence.Embeddable

private const val MINIMUM_QUANTITY_NUMBER = 0L

@Embeddable
data class Quantity(
    @Column(nullable = false) val quantity: Long
) {
    init {
        require(quantity >= MINIMUM_QUANTITY_NUMBER)
    }
}
