package com.dailycoffee.products.domain

import com.dailycoffee.products.infra.FakeProfanityClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.util.UUID

internal class ProductTest {

    private val profanityClient = FakeProfanityClient()

    @Test
    fun `상품을 등록할 수 있다`() {
        val actual = Product(UUID.randomUUID(), DisplayedName("아이스 아메리카노", profanityClient), 58_000L)
        assertThat(actual).isNotNull
        assertAll(
            { assertThat(actual.id).isNotNull },
            { assertThat(actual.name).isEqualTo(DisplayedName("아이스 아메리카노", profanityClient)) },
            { assertThat(actual.price).isEqualTo(Price(58_000L)) }
        )
    }
}
