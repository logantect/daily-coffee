package com.dailycoffee

import com.dailycoffee.products.domain.DisplayedName
import com.dailycoffee.products.domain.Product

fun product(name: DisplayedName, price: Long) = Product(name, price)
