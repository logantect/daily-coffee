package com.dailycoffee

import com.dailycoffee.products.domain.DisplayedName
import com.dailycoffee.products.domain.Product
import java.util.UUID

fun product(name: DisplayedName, price: Long) = Product(UUID.randomUUID(), name, price)
