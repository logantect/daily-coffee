package com.dailycoffee.menus.domain

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

private const val ZERO_NUMBER = 0L

@Embeddable
data class Price(
    @Column(nullable = false) private val amount: BigDecimal
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

    companion object {
        val ZERO = Price(ZERO_NUMBER)
    }
}
