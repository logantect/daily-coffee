package com.dailycoffee.menus.infra

import com.dailycoffee.menus.domain.Menu
import com.dailycoffee.menus.domain.MenuRepository
import java.util.Optional
import java.util.UUID

class InMemoryMenuRepository : MenuRepository {
    override fun save(menu: Menu): Menu {
        storage[menu.id] = menu
        return menu
    }

    override fun findById(id: UUID): Optional<Menu> {
        return Optional.ofNullable(storage[id])
    }

    override fun findAll(): List<Menu> {
        return storage.values.toList()
    }

    override fun findAllByIdIn(ids: List<UUID>): List<Menu> {
        return storage.values.stream()
            .filter { ids.contains(it.id) }
            .map { it }
            .toList()
    }

    override fun deleteAll() {
        storage.clear()
    }

    companion object {
        private val storage: MutableMap<UUID, Menu> = HashMap()
    }
}
