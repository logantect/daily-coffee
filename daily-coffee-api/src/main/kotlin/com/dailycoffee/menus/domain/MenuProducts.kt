package com.dailycoffee.menus.domain

import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Embeddable
class MenuProducts(
    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
    )
    @JoinColumn(name = "menu_id")
    private var _menuProducts: MutableList<MenuProduct> = mutableListOf()
) {
    val menuProducts: List<MenuProduct>
        get() = _menuProducts.toList()

    init {
        require(_menuProducts.isNotEmpty())
    }

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

    private fun totalPrice(): Price = menuProducts.stream()
        .map { it.amount() }
        .reduce(Price.ZERO, Price::plus)
}
