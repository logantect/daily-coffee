package com.dailycoffee.menus.domain

private const val MINIMUM_QUANTITY_NUMBER = 0L

data class Quantity(
    private val quantity: Long
) {
    init {
        require(quantity >= MINIMUM_QUANTITY_NUMBER)
    }
}
