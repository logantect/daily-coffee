package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.menus.domain.Menu
import com.dailycoffee.menus.domain.MenuRepository
import com.dailycoffee.products.domain.ProductRepository
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils.isEmpty
import java.util.UUID

@Component
class OrderValidator(
    private val productRepository: ProductRepository,
    private val menuRepository: MenuRepository,
) {
    fun validate(order: Order) {
        validate(order, getMenus(order.orderLineItems))
    }

    fun validate(order: Order, menus: Map<UUID, Menu>) {
        val orderLineItems = order.orderLineItems
        orderLineItems.orderLineItems.forEach {
            validateOrderLineItem(it, menus[it.menuId]!!)
        }
    }

    fun validateOrderLineItem(orderLineItem: OrderLineItem, menu: Menu) {
        if (menu.isHidden()) {
            throw IllegalArgumentException("메뉴가 존재하지 않습니다.")
        }
        if (!menu.isSatisfiedBy(orderLineItem.price.amount)) {
            throw IllegalArgumentException("메뉴 가격이 일치하지 않습니다.")
        }
    }

    private fun getMenus(orderLineItems: OrderLineItems): Map<UUID, Menu> {
        if (isEmpty(orderLineItems.orderLineItems)) {
            throw IllegalArgumentException("주문 메뉴가 없습니다.")
        }

        val menus = menuRepository.findAllByIdIn(orderLineItems.toMenuIds())
        if (orderLineItems.orderLineItems.size != menus.size) {
            throw IllegalArgumentException("메뉴가 존재하지 않습니다.")
        }
        return menus.associateBy { it.id }
    }
}
