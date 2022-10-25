package com.dailycoffee.deliveryorders.domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("주문 항목 수량 테스트")
internal class QuantityTest {

    @Test
    fun `주문 항목의 수량은 0 이상이어야 한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Quantity(-1) }
    }
}
