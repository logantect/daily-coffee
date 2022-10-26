package com.dailycoffee.products.interfaces

import com.dailycoffee.products.application.ChangePriceRequest
import com.dailycoffee.products.application.CreateProductRequest
import com.dailycoffee.products.application.ProductResponse
import com.dailycoffee.products.application.ProductService
import com.dailycoffee.products.domain.Price
import com.dailycoffee.support.interfaces.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.util.UUID
import javax.validation.Valid

@RequestMapping("/api/v1/products")
@RestController
class ProductRestController(
    private val productService: ProductService
) {
    @PostMapping
    fun create(@RequestBody @Valid request: CreateProductRequest): ResponseEntity<ApiResponse<ProductResponse>> {
        val product = productService.create(request)
        return ResponseEntity.created(URI.create("/api/v1/products/${product.id}"))
            .body(ApiResponse.success(product))
    }

    @PatchMapping("/{productId}/price")
    fun changePrice(
        @PathVariable productId: UUID,
        @RequestBody @Valid request: ChangePriceRequest
    ): ResponseEntity<Unit> {
        productService.changePrice(productId, Price(request.price))
        return ResponseEntity.ok().build()
    }
}
