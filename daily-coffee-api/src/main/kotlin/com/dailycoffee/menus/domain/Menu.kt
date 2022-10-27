package com.dailycoffee.menus.domain

import com.dailycoffee.support.utils.IdGenerator
import java.math.BigDecimal
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "menu")
class Menu(
    @Id @Column(columnDefinition = "BINARY(16)") val id: UUID,
    @Column(nullable = false) val name: String,
    price: Price = Price.ZERO,
    displayed: Boolean = true,
    @Column(columnDefinition = "BINARY(16)") val menuGroupId: UUID,
    @Embedded val menuProducts: MenuProducts,
) {
    @Embedded
    var price: Price = price
        protected set

    var displayed: Boolean = displayed
        protected set

    init {
        validatePrice(price)
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
