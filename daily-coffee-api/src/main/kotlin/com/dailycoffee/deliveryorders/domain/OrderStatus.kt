package com.dailycoffee.deliveryorders.domain

enum class OrderStatus {
    WAITING, ACCEPTED, SERVED, DELIVERING, DELIVERED, COMPLETED;

    fun isWaiting(): Boolean {
        return this == WAITING
    }

    fun isAccepted(): Boolean {
        return this == ACCEPTED
    }

    fun isServed(): Boolean {
        return this == SERVED
    }

    fun isDelivering(): Boolean {
        return this == DELIVERING
    }
}
