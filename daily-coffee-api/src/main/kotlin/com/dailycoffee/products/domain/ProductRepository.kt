package com.dailycoffee.products.domain

import java.util.Optional
import java.util.UUID

interface ProductRepository {
    fun save(product: Product): Product

    fun findById(id: UUID): Optional<Product>

    fun findByIdOrNull(id: UUID): Product?

    fun findAll(): List<Product>
}
