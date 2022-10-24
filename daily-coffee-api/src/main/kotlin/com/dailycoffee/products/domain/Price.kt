package com.dailycoffee.products.domain

import java.math.BigDecimal
import java.util.Objects

data class Price(
    private val amount: BigDecimal
) {
    init {
        require(Objects.nonNull(amount) && amount >= BigDecimal.ZERO)
    }

    constructor(amount: Long) : this(BigDecimal.valueOf(amount))
}
