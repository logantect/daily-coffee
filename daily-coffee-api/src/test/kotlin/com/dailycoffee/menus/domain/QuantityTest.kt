package com.dailycoffee.menus.domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class QuantityTest {

    @Test
    fun `메뉴에 속한 상품의 수량은 0 이상이어야 한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Quantity(-1) }
    }
}
