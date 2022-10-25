package com.dailycoffee.deliveryorders.domain

enum class OrderStatus {
    WAITING, ACCEPTED, SERVED, DELIVERING, DELIVERED, COMPLETED;

    fun isWaiting(): Boolean {
        return this == WAITING
    }

    fun isAccepted(): Boolean {
        return this == ACCEPTED
    }
}
