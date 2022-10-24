package com.dailycoffee.menus.domain

class MenuProducts(
    val menuProducts: List<MenuProduct>
) {
    init {
        require(menuProducts.isNotEmpty())
    }

    fun isGreaterThanOrEqualsTotalPrice(price: Price): Boolean {
        val totalPrice = totalPrice()
        return totalPrice >= price
    }

    private fun totalPrice(): Price = menuProducts.stream()
        .map { it.amount() }
        .reduce(Price.ZERO, Price::plus)
}
