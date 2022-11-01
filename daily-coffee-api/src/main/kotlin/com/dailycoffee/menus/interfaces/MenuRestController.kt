package com.dailycoffee.menus.interfaces

import com.dailycoffee.menus.application.ChangePriceRequest
import com.dailycoffee.menus.application.CreateMenuRequest
import com.dailycoffee.menus.application.MenuResponse
import com.dailycoffee.menus.application.MenuService
import com.dailycoffee.support.interfaces.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID
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

    @PatchMapping("/{menuId}/price")
    fun changePrice(
        @PathVariable menuId: UUID,
        @RequestBody @Valid request: ChangePriceRequest
    ): ResponseEntity<Unit> {
        menuService.changePrice(menuId, request)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun getMenus(): ResponseEntity<ApiResponse<List<MenuResponse>>> {
        val menuGroups = menuService.findAll()
        return ResponseEntity.ok(ApiResponse.success(menuGroups))
    }
}
