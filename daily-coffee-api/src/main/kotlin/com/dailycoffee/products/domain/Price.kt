package com.dailycoffee.products.domain

import java.math.BigDecimal

private const val ZERO_NUMBER = 0L

data class Price(
    private val amount: BigDecimal
) {
    init {
        require(amount >= BigDecimal.ZERO)
    }

    constructor(amount: Long) : this(BigDecimal.valueOf(amount))

    companion object {
        val ZERO = Price(ZERO_NUMBER)
    }
}
