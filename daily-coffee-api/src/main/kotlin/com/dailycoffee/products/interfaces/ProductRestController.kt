package com.dailycoffee.products.interfaces

import com.dailycoffee.products.application.CreateProductRequest
import com.dailycoffee.products.application.ProductResponse
import com.dailycoffee.products.application.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/api/v1/products")
@RestController
class ProductRestController(
    private val productService: ProductService
) {
    @PostMapping
    fun create(@RequestBody request: CreateProductRequest): ResponseEntity<ProductResponse> {
        val product = productService.create(request)
        return ResponseEntity.created(URI.create("/api/v1/products/${product.id}"))
            .body(product)
    }
}
