package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.menus.domain.Quantity
import java.math.BigDecimal

data class Price(
    private val amount: BigDecimal
) {
    init {
        require(amount >= BigDecimal.ZERO)
    }

    constructor(amount: Long) : this(BigDecimal.valueOf(amount))

    operator fun times(quantity: Quantity): Price {
        return Price(amount * BigDecimal.valueOf(quantity.quantity))
    }

    operator fun plus(price: Price): Price {
        return Price(amount + price.amount)
    }

    operator fun compareTo(price: Price): Int {
        return amount.compareTo(price.amount)
    }
}
