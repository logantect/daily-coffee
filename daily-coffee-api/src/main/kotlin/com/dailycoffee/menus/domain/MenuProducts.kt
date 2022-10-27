package com.dailycoffee.menus.domain

import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.ForeignKey
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Embeddable
class MenuProducts(
    @OneToMany(
        cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER,
        orphanRemoval = true,
    )
    @JoinColumn(
        name = "menu_id", nullable = false, updatable = false,
        foreignKey = ForeignKey(name = "fk_menu_product_menu_id_ref_menu_id")
    )
    private var _menuProducts: MutableList<MenuProduct> = mutableListOf()
) {
    val menuProducts: List<MenuProduct>
        get() = _menuProducts.toList()

    fun isGreaterThanOrEqualsTotalPrice(price: Price): Boolean {
        val totalPrice = totalPrice()
        return totalPrice >= price
    }

    fun addMenuProduct(menuProduct: MenuProduct) {
        _menuProducts += menuProduct
    }

    fun removeMenuProduct(menuProduct: MenuProduct) {
        _menuProducts -= menuProduct
    }

    fun isNotEmpty(): Boolean {
        return menuProducts.isNotEmpty()
    }

    private fun totalPrice(): Price = menuProducts.stream()
        .map { it.amount() }
        .reduce(Price.ZERO, Price::plus)
}
