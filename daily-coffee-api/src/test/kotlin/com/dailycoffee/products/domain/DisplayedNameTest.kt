package com.dailycoffee.products.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class DisplayedNameTest {

    @Test
    fun `상품의 이름을 생성할 수 있다`() {
        val actual = DisplayedName("아이스 아메리카노")
        Assertions.assertThat(actual).isEqualTo(DisplayedName("아이스 아메리카노"))
    }
}
