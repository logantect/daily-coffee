package com.dailycoffee.products.domain

import com.dailycoffee.utils.IdGenerator
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "product")
class Product(
    @Id @Column(columnDefinition = "BINARY(16)") val id: UUID,
    @Embedded val name: DisplayedName,
    price: Price = Price.ZERO
) {
    @Embedded
    var price: Price = price
        protected set

    constructor(name: DisplayedName, price: Long) : this(IdGenerator.createId(), name, Price(price))

    fun changePrice(price: Price) {
        this.price = price
    }
}
