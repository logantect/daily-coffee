package com.dailycoffee.deliveryorders.interfaces

import com.dailycoffee.deliveryorders.application.OrderRequest
import com.dailycoffee.deliveryorders.application.OrderResponse
import com.dailycoffee.deliveryorders.application.OrderService
import com.dailycoffee.support.interfaces.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RequestMapping("/api/v1/orders")
@RestController
class OrderRestController(
    private val orderService: OrderService
) {
    @PostMapping
    fun create(@RequestBody @Valid request: OrderRequest): ResponseEntity<ApiResponse<OrderResponse>> {
        val order = orderService.create(request)
        return ResponseEntity.created(URI.create("/api/v1/orders/${order.id}"))
            .body(ApiResponse.success(order))
    }
}
