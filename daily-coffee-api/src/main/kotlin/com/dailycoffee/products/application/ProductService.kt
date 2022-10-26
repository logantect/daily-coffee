package com.dailycoffee.products.application

import com.dailycoffee.products.domain.DisplayedName
import com.dailycoffee.products.domain.Price
import com.dailycoffee.products.domain.Product
import com.dailycoffee.products.domain.ProductRepository
import com.dailycoffee.products.domain.ProfanityClient
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

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

    fun changePrice(id: UUID, price: Price) {
        val product = productRepository.findByIdOrNull(id)
            ?: throw NoSuchElementException("상품이 존재하지 않습니다. id: $id")
        product.changePrice(price)
    }

    fun findAll(): List<ProductResponse> {
        return productRepository.findAll()
            .map { ProductResponse(it) }
    }
}
