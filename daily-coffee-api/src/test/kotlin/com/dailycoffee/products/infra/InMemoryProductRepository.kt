package com.dailycoffee.products.infra

import com.dailycoffee.products.domain.Product
import com.dailycoffee.products.domain.ProductRepository
import java.util.Optional
import java.util.UUID

class InMemoryProductRepository : ProductRepository {
    override fun save(product: Product): Product {
        storage[product.id] = product
        return product
    }

    override fun findById(id: UUID): Optional<Product> {
        return Optional.ofNullable(storage[id])
    }

    override fun findAll(): List<Product> {
        return storage.values.toList()
    }

    companion object {
        private val storage: MutableMap<UUID, Product> = HashMap()
    }
}
