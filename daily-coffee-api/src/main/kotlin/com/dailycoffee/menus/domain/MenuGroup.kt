package com.dailycoffee.menus.domain

import com.dailycoffee.utils.IdGenerator
import java.util.UUID

class MenuGroup(
    val id: UUID,
    name: MenuGroupName,
) {
    var name: MenuGroupName = name
        private set

    constructor(name: String) : this(IdGenerator.createId(), MenuGroupName(name))
}
