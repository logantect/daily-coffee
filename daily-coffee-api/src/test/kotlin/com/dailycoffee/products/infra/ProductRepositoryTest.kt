package com.dailycoffee.products.infra

import com.dailycoffee.RepositoryTest
import com.dailycoffee.product
import com.dailycoffee.products.domain.DisplayedName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("상품 저장소 테스트")
@RepositoryTest
internal class ProductRepositoryTest(
    private val productRepository: JpaProductRepository,
) {
    private lateinit var profanityClient: FakeProfanityClient

    @BeforeEach
    internal fun setUp() {
        profanityClient = FakeProfanityClient()
    }

    @AfterEach
    internal fun tearDown() {
        productRepository.deleteAll()
    }

    @Test
    fun `상품의 목록을 조회할 수 있다`() {
        productRepository.save(product(DisplayedName("아이스 아메리카노", profanityClient), 5_800L))
        productRepository.save(product(DisplayedName("아이스 카페라떼", profanityClient), 6_000L))

        val actual = productRepository.findAll()
        assertThat(actual).hasSize(2)
    }
}
