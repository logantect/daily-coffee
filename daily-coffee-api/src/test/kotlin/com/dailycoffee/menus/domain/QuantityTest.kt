package com.dailycoffee.menus.domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("메뉴 수량 테스트")
internal class QuantityTest {

    @Test
    fun `메뉴에 속한 상품의 수량은 0 이상이어야 한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Quantity(-1) }
    }
}
