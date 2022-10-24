package com.dailycoffee.menus.domain

import com.dailycoffee.utils.IdGenerator
import java.math.BigDecimal
import java.util.UUID

class Menu(
    val id: UUID,
    val name: String,
    val price: BigDecimal,
    val displayed: Boolean,
    val menuGroupId: UUID,
    val menuProducts: List<MenuProduct>,
) {

    init {
        require(menuProducts.isNotEmpty())
    }

    constructor(
        name: String,
        price: BigDecimal,
        displayed: Boolean,
        menuGroupId: UUID,
        menuProducts: List<MenuProduct>,
    ) : this(
        IdGenerator.createId(),
        name,
        price,
        displayed,
        menuGroupId,
        menuProducts,
    )
}
