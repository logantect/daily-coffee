package com.dailycoffee.products.domain

import com.dailycoffee.utils.IdGenerator
import java.util.UUID

class Product(
    val id: UUID,
    val name: DisplayedName,
    price: Price = Price.ZERO
) {
    var price: Price = price
        private set

    constructor(name: DisplayedName, price: Long) : this(IdGenerator.createId(), name, Price(price))

    fun changePrice(price: Price) {
        this.price = price
    }
}
