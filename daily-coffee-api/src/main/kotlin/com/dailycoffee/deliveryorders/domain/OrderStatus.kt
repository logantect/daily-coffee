package com.dailycoffee.deliveryorders.domain

enum class OrderStatus {
    WAITING, COMPLETED, SERVED, ACCEPTED;

    fun isWaiting(): Boolean {
        return this == WAITING
    }
}
