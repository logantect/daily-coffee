package com.dailycoffee.products.domain

import java.util.UUID

class Product(
    val id: UUID,
    val name: DisplayedName,
    price: Price = Price.ZERO
) {
    var price: Price = price
        private set

    constructor(id: UUID, name: DisplayedName, price: Long) : this(id, name, Price(price))

    fun changePrice(price: Price) {
        this.price = price
    }
}
