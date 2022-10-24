package com.dailycoffee.menus.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.math.BigDecimal

internal class PriceTest {

    @ParameterizedTest
    @ValueSource(longs = [0, 16000L])
    fun `메뉴의 가격을 생성할 수 있다`(value: Long) {
        val actual = Price(BigDecimal.valueOf(value))
        assertThat(actual).isEqualTo(Price(value))
    }

    @Test
    fun `메뉴의 가격은 0원 이상이어야 한다`() {
        assertThatIllegalArgumentException()
            .isThrownBy { Price(-1) }
    }
}
