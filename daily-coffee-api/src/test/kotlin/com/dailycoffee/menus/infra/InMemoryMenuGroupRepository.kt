package com.dailycoffee.menus.infra

import com.dailycoffee.menus.domain.MenuGroup
import com.dailycoffee.menus.domain.MenuGroupRepository
import java.util.Optional
import java.util.UUID

class InMemoryMenuGroupRepository : MenuGroupRepository {
    override fun save(menuGroup: MenuGroup): MenuGroup {
        storage[menuGroup.id] = menuGroup
        return menuGroup
    }

    override fun findById(id: UUID): Optional<MenuGroup> {
        return Optional.ofNullable(storage[id])
    }

    override fun findAll(): List<MenuGroup> {
        return storage.values.toList()
    }

    override fun deleteAll() {
        storage.clear()
    }

    companion object {
        private val storage: MutableMap<UUID, MenuGroup> = HashMap()
    }
}
