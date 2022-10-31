package com.dailycoffee.menus.application

import com.dailycoffee.menus.domain.MenuGroup
import com.dailycoffee.menus.domain.MenuGroupRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MenuGroupService(
    private val menuGroupRepository: MenuGroupRepository
) {
    fun create(command: CreateMenuGroupRequest): MenuGroupResponse {
        val menuGroupRequest = menuGroupRepository.save(
            MenuGroup(
                name = command.name
            )
        )
        return MenuGroupResponse(menuGroupRepository.save(menuGroupRequest))
    }
}
