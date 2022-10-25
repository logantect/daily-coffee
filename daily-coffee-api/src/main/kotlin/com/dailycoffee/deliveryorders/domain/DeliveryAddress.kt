package com.dailycoffee.deliveryorders.domain

data class DeliveryAddress(
    val address: String,
) {
    init {
        require(address.isNotBlank())
    }
}
