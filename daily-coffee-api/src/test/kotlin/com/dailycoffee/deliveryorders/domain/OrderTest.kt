package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.utils.IdGenerator
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

@DisplayName("주문 테스트")
internal class OrderTest {

    @Nested
    @DisplayName("주문 등록")
    inner class OrderMenu {

        @Test
        fun `1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다`() {
            val actual = order(listOf(orderLineItem(IdGenerator.createId(), BigDecimal.valueOf(4_500L), 1)))

            assertThat(actual).isNotNull
            SoftAssertions().apply {
                assertThat(actual.id).isNotNull
                assertThat(actual.orderLineItems.orderLineItems).hasSize(1)
            }.assertAll()
        }
    }

    fun order(orderLineItems: List<OrderLineItem>): Order {
        return Order(orderLineItems)
    }

    fun orderLineItem(menuId: UUID, price: BigDecimal, quantity: Long): OrderLineItem {
        return OrderLineItem(menuId, price, quantity)
    }
}
