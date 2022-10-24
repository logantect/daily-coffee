package com.dailycoffee.menus.domain

import com.dailycoffee.utils.IdGenerator
import java.util.UUID

class MenuGroup(
    val id: UUID,
    name: String,
) {
    var name: String = name
        private set

    init {
        require(name.isNotBlank())
    }

    constructor(name: String) : this(IdGenerator.createId(), name)
}
