package com.dailycoffee.products.domain

import java.util.UUID

class Product(
    val id: UUID,
    val name: DisplayedName,
    val price: Price
) {
    constructor(id: UUID, name: DisplayedName, price: Long) : this(id, name, Price(price))
}
