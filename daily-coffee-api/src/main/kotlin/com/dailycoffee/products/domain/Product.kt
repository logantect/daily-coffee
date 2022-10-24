package com.dailycoffee.products.domain

import java.util.UUID

class Product(
    val id: UUID,
    val name: String,
    val price: Price
)
