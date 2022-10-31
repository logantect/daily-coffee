package com.dailycoffee.menus.application

import com.dailycoffee.menus.domain.MenuGroup
import java.util.UUID

data class CreateMenuGroupRequest(
    val name: String,
)

data class MenuGroupResponse(
    val id: UUID,
    val name: String,
) {
    constructor(menuGroup: MenuGroup) : this(
        id = menuGroup.id,
        name = menuGroup.name.name,
    )
}
