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
        val orderLineItems = order.orderLineItems
        if (isEmpty(orderLineItems.orderLineItems)) {
            throw IllegalArgumentException("주문 메뉴가 없습니다.")
        }

        val orderMenus = menuRepository.findAllByIdIn(orderLineItems.toMenuIds())
        if (orderLineItems.orderLineItems.size != orderMenus.size) {
            throw IllegalArgumentException("메뉴가 존재하지 않습니다.")
        }
    }
}
