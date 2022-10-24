package com.dailycoffee.products.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.math.BigDecimal
import java.util.UUID

internal class ProductTest {

    @Test
    fun `상품을 등록할 수 있다`() {
        val actual = Product(UUID.randomUUID(), "아이스 아메리카노", Price(58_000L))
        assertThat(actual).isNotNull
        assertAll(
            { assertThat(actual.id).isNotNull },
            { assertThat(actual.name).isEqualTo("아이스 아메리카노") },
            { assertThat(actual.price).isEqualTo(BigDecimal.valueOf(58_000L)) }
        )
    }
}
