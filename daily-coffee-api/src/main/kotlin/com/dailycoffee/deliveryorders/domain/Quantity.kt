package com.dailycoffee.deliveryorders.domain

private const val MINIMUM_QUANTITY_NUMBER = 0L

data class Quantity(
    val quantity: Long
) {
    init {
        require(quantity >= MINIMUM_QUANTITY_NUMBER)
    }
}
