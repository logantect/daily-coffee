package com.dailycoffee.products.application

import com.dailycoffee.products.domain.DisplayedName
import com.dailycoffee.products.domain.Product
import com.dailycoffee.products.domain.ProductRepository
import com.dailycoffee.products.domain.ProfanityClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val profanityClient: ProfanityClient,
) {
    fun create(command: ProductCreateCommand): ProductResponse {
        val product = productRepository.save(
            Product(
                name = DisplayedName(command.name, profanityClient),
                price = command.price
            )
        )
        return ProductResponse(product)
    }
}
