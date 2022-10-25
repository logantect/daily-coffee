package com.dailycoffee.menus.domain

import com.dailycoffee.utils.IdGenerator
import java.math.BigDecimal
import java.util.UUID

class Menu(
    val id: UUID,
    val name: String,
    price: Price = Price.ZERO,
    displayed: Boolean,
    val menuGroupId: UUID,
    val menuProducts: MenuProducts,
) {
    var price: Price = price
        private set

    var displayed: Boolean = displayed
        private set

    init {
        validatePrice(price)
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
        Price(price),
        displayed,
        menuGroupId,
        MenuProducts(menuProducts),
    )

    fun changePrice(price: Price) {
        validatePrice(price)
        this.price = price
    }

    fun display() {
        this.displayed = true
    }

    private fun validatePrice(price: Price) {
        require(menuProducts.isGreaterThanOrEqualsTotalPrice(price))
    }
}
