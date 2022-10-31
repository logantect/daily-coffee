package com.dailycoffee.menus.interfaces

import com.dailycoffee.menus.application.CreateMenuRequest
import com.dailycoffee.menus.application.MenuResponse
import com.dailycoffee.menus.application.MenuService
import com.dailycoffee.support.interfaces.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RequestMapping("/api/v1/menus")
@RestController
class MenuRestController(
    private val menuService: MenuService
) {
    @PostMapping
    fun create(@RequestBody @Valid request: CreateMenuRequest): ResponseEntity<ApiResponse<MenuResponse>> {
        val menu = menuService.create(request)
        return ResponseEntity.created(URI.create("/api/v1/menus/${menu.id}"))
            .body(ApiResponse.success(menu))
    }
}
