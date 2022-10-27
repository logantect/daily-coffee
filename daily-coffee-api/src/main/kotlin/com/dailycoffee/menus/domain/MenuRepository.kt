package com.dailycoffee.menus.domain

import java.util.Optional
import java.util.UUID

fun MenuRepository.findByIdOrNull(id: UUID): Menu? = findById(id).orElse(null)

interface MenuRepository {
    fun save(menu: Menu): Menu
    fun findById(id: UUID): Optional<Menu>
    fun findAll(): List<Menu>
}
