package com.dailycoffee.products.domain

import com.dailycoffee.product
import com.dailycoffee.products.infra.FakeProfanityClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("상품 테스트")
internal class ProductTest {

    private val profanityClient = FakeProfanityClient()

    @Test
    fun `상품을 등록할 수 있다`() {
        val actual = product(DisplayedName("아이스 아메리카노", profanityClient), 5_800L)

        assertThat(actual).isNotNull
        assertAll(
            { assertThat(actual.id).isNotNull },
            { assertThat(actual.name).isEqualTo(DisplayedName("아이스 아메리카노", profanityClient)) },
            { assertThat(actual.price).isEqualTo(Price(5_800L)) }
        )
    }

    @Test
    fun `상품의 가격을 변경할 수 있다`() {
        val actual = product(DisplayedName("아이스 아메리카노", profanityClient), 5_800L)
        val expected = Price(6_000L)
        actual.changePrice(expected)

        assertThat(actual.price).isEqualTo(expected)
    }
}
