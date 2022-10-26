package com.dailycoffee.menus.domain

import com.dailycoffee.support.utils.IdGenerator
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
        MenuProducts(menuProducts.toMutableList()),
    )

    fun changePrice(price: Price) {
        validatePrice(price)
        this.price = price
    }

    fun display() {
        validatePrice(price)
        this.displayed = true
    }

    fun hide() {
        this.displayed = false
    }

    fun addMenuProduct(menuProduct: MenuProduct) {
        menuProducts.addMenuProduct(menuProduct)
    }

    fun removeMenuProduct(menuProduct: MenuProduct) {
        menuProducts.removeMenuProduct(menuProduct)
    }

    private fun validatePrice(price: Price) {
        require(menuProducts.isGreaterThanOrEqualsTotalPrice(price))
    }
}
