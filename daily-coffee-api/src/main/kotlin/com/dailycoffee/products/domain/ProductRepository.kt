package com.dailycoffee.products.domain

import java.util.Optional
import java.util.UUID

fun ProductRepository.findByIdOrNull(id: UUID): Product? = findById(id).orElse(null)

interface ProductRepository {
    fun save(product: Product): Product

    fun findById(id: UUID): Optional<Product>

    fun findAll(): List<Product>

    fun deleteAll()
}
