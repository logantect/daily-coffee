package com.dailycoffee.deliveryorders.domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("주문 항목 수량 테스트")
internal class QuantityTest {

    @ParameterizedTest
    @ValueSource(longs = [0L, -1L])
    fun `주문 항목의 수량은 1 이상이어야 한다`(quantity: Long) {
        assertThatIllegalArgumentException()
            .isThrownBy { Quantity(quantity) }
    }
}
