package com.dailycoffee.menus.interfaces

import com.dailycoffee.menus.application.CreateMenuGroupRequest
import com.dailycoffee.menus.application.MenuGroupResponse
import com.dailycoffee.menus.application.MenuGroupService
import com.dailycoffee.support.interfaces.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RequestMapping("/api/v1/menu-groups")
@RestController
class MenuGroupRestController(
    private val menuGroupService: MenuGroupService
) {
    @PostMapping
    fun create(@RequestBody @Valid request: CreateMenuGroupRequest): ResponseEntity<ApiResponse<MenuGroupResponse>> {
        val menuGroup = menuGroupService.create(request)
        return ResponseEntity.created(URI.create("/api/v1/menu-groups/${menuGroup.id}"))
            .body(ApiResponse.success(menuGroup))
    }
}
