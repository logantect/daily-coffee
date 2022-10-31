package com.dailycoffee.menus.application

import com.dailycoffee.menus.domain.Menu
import com.dailycoffee.menus.domain.MenuProduct
import java.math.BigDecimal
import java.util.UUID

data class CreateMenuRequest(
    val name: String,
    val price: BigDecimal,
    val displayed: Boolean,
    val menuGroupId: UUID,
    val menuProducts: List<MenuProductRequest>
)

data class ChangePriceRequest(
    val price: BigDecimal,
)

data class MenuProductRequest(
    val productId: UUID,
    val price: BigDecimal,
    val quantity: Long
)

data class MenuResponse(
    val id: UUID,
    val name: String,
    val price: BigDecimal,
    val displayed: Boolean,
    val menuGroupId: UUID,
    val menuProducts: List<MenuProductResponse>
) {
    constructor(entity: Menu) : this(
        id = entity.id,
        name = entity.name,
        price = entity.price.amount,
        displayed = entity.displayed,
        menuGroupId = entity.menuGroupId,
        menuProducts = entity.menuProducts.menuProducts.map { MenuProductResponse(it) }
    )
}

data class MenuProductResponse(
    val productId: UUID,
    val price: BigDecimal,
    val quantity: Long
) {
    constructor(entity: MenuProduct) : this(
        productId = entity.productId,
        price = entity.price.amount,
        quantity = entity.quantity.quantity,
    )
}
