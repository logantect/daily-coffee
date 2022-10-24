package com.dailycoffee.products.domain

import java.math.BigDecimal

data class Price(
    private val amount: BigDecimal
) {
    init {
        require(amount >= BigDecimal.ZERO)
    }

    constructor(amount: Long) : this(BigDecimal.valueOf(amount))
}
