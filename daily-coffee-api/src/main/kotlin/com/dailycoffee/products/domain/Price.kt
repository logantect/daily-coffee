package com.dailycoffee.products.domain

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

    companion object {
        val ZERO = Price(ZERO_NUMBER)
    }
}
