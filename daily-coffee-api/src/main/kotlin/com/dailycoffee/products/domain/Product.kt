package com.dailycoffee.products.domain

import java.math.BigDecimal
import java.util.*

class Product(
    val id: UUID,
    val name: String,
    val price: BigDecimal
) {
}