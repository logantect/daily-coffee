package com.dailycoffee.products.application

import com.dailycoffee.product
import com.dailycoffee.products.domain.DisplayedName
import com.dailycoffee.products.domain.Price
import com.dailycoffee.products.domain.ProductRepository
import com.dailycoffee.products.infra.FakeProfanityClient
import com.dailycoffee.products.infra.InMemoryProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple.tuple
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.math.BigDecimal

@DisplayName("상품 서비스 테스트")
internal class ProductServiceTest {
    lateinit var productService: ProductService
    lateinit var productRepository: ProductRepository
    lateinit var profanityClient: FakeProfanityClient

    @BeforeEach
    fun setUp() {
        profanityClient = FakeProfanityClient()
        productRepository = InMemoryProductRepository()
        productService = ProductService(productRepository, profanityClient)
    }

    @Nested
    @DisplayName("상품 등록")
    inner class ProductCreate {

        @Test
        fun `상품을 등록할 수 있다`() {
            val actual = productService.create(
                ProductCreateCommand(
                    name = "아이스 카페 아메리카노",
                    price = BigDecimal.valueOf(4_500L),
                )
            )
            assertThat(actual.id).isNotNull
            assertThat(actual.name).isEqualTo(DisplayedName("아이스 카페 아메리카노", profanityClient))
            assertThat(actual.price).isEqualTo(Price(4_500L))
        }
    }

    @Nested
    @DisplayName("상품 가격 변경")
    inner class ProductChangePrice {

        @Test
        fun `상품의 가격을 변경할 수 있다`() {
            val actual = productService.create(
                ProductCreateCommand(
                    name = "아이스 카페 아메리카노",
                    price = BigDecimal.valueOf(4_500L),
                )
            )
            assertDoesNotThrow { productService.changePrice(actual.id, Price(5_500L)) }
        }
    }

    @Nested
    @DisplayName("상품 조회")
    inner class ProductRetrieve {

        @Test
        fun `상품의 목록을 조회할 수 있다`() {
            productRepository.save(product(DisplayedName("아이스 아메리카노", profanityClient), 5_800L))
            productRepository.save(product(DisplayedName("아이스 카페라떼", profanityClient), 6_000L))

            val actual = productService.findAll()
            assertThat(actual).hasSize(2)
            assertThat(actual).map(ProductResponse::name, ProductResponse::price)
                .contains(
                    tuple(DisplayedName("아이스 아메리카노", profanityClient), Price(5_800L)),
                    tuple(DisplayedName("아이스 카페라떼", profanityClient), Price(6_000L)),
                )
        }
    }
}
