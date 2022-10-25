package com.dailycoffee.products.infra

import com.dailycoffee.products.domain.Product
import com.dailycoffee.products.domain.ProductRepository
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface JpaProductRepository : ProductRepository, JpaRepository<Product, UUID>
