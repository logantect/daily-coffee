package com.dailycoffee.menus.domain

import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Embeddable

private const val ZERO_NUMBER = 0L

@Embeddable
data class Price(
    @Column(nullable = false) val amount: BigDecimal
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Price

        if (amount.toDouble() != other.amount.toDouble()) return false

        return true
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }

    companion object {
        val ZERO = Price(ZERO_NUMBER)
    }
}
