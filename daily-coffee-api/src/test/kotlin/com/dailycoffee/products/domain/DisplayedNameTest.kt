package com.dailycoffee.products.domain

import com.dailycoffee.products.infra.FakeProfanityClient
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class DisplayedNameTest {

    private val profanityClient = FakeProfanityClient()

    @Test
    fun `상품의 이름을 생성할 수 있다`() {
        val actual = DisplayedName("아이스 아메리카노", profanityClient)
        assertThat(actual).isEqualTo(DisplayedName("아이스 아메리카노", profanityClient))
    }

    @ValueSource(strings = ["비속어", "욕설이 포함된 이름"])
    @ParameterizedTest
    fun `상품의 이름이 올바르지 않으면 등록할 수 없다`(name: String) {
        assertThatIllegalArgumentException()
            .isThrownBy { DisplayedName(name, profanityClient) }
    }
}
