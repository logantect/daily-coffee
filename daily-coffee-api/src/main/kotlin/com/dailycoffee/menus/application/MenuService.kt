package com.dailycoffee.menus.application

import com.dailycoffee.menus.domain.Menu
import com.dailycoffee.menus.domain.MenuProduct
import com.dailycoffee.menus.domain.MenuRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MenuService(
    private val menuRepository: MenuRepository
) {
    fun create(command: CreateMenuRequest): MenuResponse {
        val menu = Menu(
            name = command.name,
            price = command.price,
            displayed = command.displayed,
            menuGroupId = command.menuGroupId,
            menuProducts = command.menuProducts.map {
                MenuProduct(
                    productId = it.productId,
                    price = it.price,
                    quantity = it.quantity
                )
            }
        )
        return MenuResponse(menuRepository.save(menu))
    }
}
