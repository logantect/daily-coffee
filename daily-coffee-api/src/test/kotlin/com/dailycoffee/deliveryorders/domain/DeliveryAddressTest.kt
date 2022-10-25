package com.dailycoffee.deliveryorders.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

internal class DeliveryAddressTest {

    @Test
    fun `배달 주소를 생성할 수 있다`() {
        val actual = DeliveryAddress("서울특별시 강남구 논현로 656")
        assertThat(actual).isEqualTo(DeliveryAddress("서울특별시 강남구 논현로 656"))
    }

    @ParameterizedTest
    @EmptySource
    fun `배달 주소는 비어있을 수 없다`(address: String) {
        assertThatIllegalArgumentException()
            .isThrownBy {
                DeliveryAddress(address)
            }
    }
}
