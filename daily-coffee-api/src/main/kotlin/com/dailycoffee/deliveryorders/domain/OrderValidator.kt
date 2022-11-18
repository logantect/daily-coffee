package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.menus.domain.MenuRepository
import com.dailycoffee.products.domain.ProductRepository
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils.isEmpty

@Component
class OrderValidator(
    private val productRepository: ProductRepository,
    private val menuRepository: MenuRepository,
) {
    fun validate(order: Order) {
        if (isEmpty(order.getOrderLineItems())) {
            throw IllegalArgumentException("주문 메뉴가 없습니다.")
        }
    }
}
