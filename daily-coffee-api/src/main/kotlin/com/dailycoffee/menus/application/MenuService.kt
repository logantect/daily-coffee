package com.dailycoffee.menus.application

import com.dailycoffee.menus.domain.Menu
import com.dailycoffee.menus.domain.MenuProduct
import com.dailycoffee.menus.domain.MenuRepository
import com.dailycoffee.menus.domain.Price
import com.dailycoffee.menus.domain.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

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

    fun changePrice(id: UUID, command: ChangePriceRequest) {
        val menu = menuRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("메뉴가 존재하지 않습니다. id: $id")
        menu.changePrice(Price(command.price))
    }

    fun findAll(): List<MenuResponse> {
        return menuRepository.findAll().map { MenuResponse(it) }
    }
}
