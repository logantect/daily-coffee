package com.dailycoffee.menus.domain

import com.dailycoffee.products.domain.Product
import com.dailycoffee.products.domain.ProductRepository
import java.util.Optional
import java.util.UUID

fun ProductRepository.findByIdOrNull(id: UUID): Product? = findById(id).orElse(null)

interface MenuGroupRepository {
    fun save(menuGroup: MenuGroup): MenuGroup
    fun findById(id: UUID): Optional<MenuGroup>
    fun findAll(): List<MenuGroup>
}
