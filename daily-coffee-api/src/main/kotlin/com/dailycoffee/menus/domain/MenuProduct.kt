package com.dailycoffee.menus.domain

import com.dailycoffee.utils.IdGenerator
import java.math.BigDecimal
import java.util.UUID

class MenuProduct(
    val id: UUID,
    val productId: UUID,
    val price: Price,
    val quantity: Quantity,
) {
    constructor(productId: UUID, price: BigDecimal, quantity: Long) : this(
        IdGenerator.createId(),
        productId,
        Price(price),
        Quantity(quantity),
    )

    fun amount(): Price {
        return price.times(quantity)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MenuProduct

        if (productId != other.productId) return false
        if (price != other.price) return false
        if (quantity != other.quantity) return false

        return true
    }

    override fun hashCode(): Int {
        var result = productId.hashCode()
        result = 31 * result + price.hashCode()
        result = 31 * result + quantity.hashCode()
        return result
    }
}
