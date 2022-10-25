package com.dailycoffee.deliveryorders.domain

import com.dailycoffee.order
import com.dailycoffee.orderLineItem
import com.dailycoffee.utils.IdGenerator
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.EnumSource
import java.math.BigDecimal

@DisplayName("주문 테스트")
internal class OrderTest {

    @Nested
    @DisplayName("주문 등록")
    inner class OrderMenu {

        @Test
        fun `1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다`() {
            val actual = order(
                "서울특별시 강남구 논현로 656",
                listOf(orderLineItem(IdGenerator.createId(), BigDecimal.valueOf(4_500L), 1))
            )

            assertThat(actual).isNotNull
            SoftAssertions().apply {
                assertThat(actual.id).isNotNull
                assertThat(actual.orderLineItems.orderLineItems).hasSize(1)
            }.assertAll()
        }

        @ParameterizedTest
        @EmptySource
        fun `배달 주소가 올바르지 않으면 배달 주문을 등록할 수 없다`(deliveryAddress: String) {
            assertThatIllegalArgumentException()
                .isThrownBy {
                    order(
                        deliveryAddress,
                        listOf(orderLineItem(IdGenerator.createId(), BigDecimal.valueOf(4_500L), 1))
                    )
                }
        }

        @Test
        fun `주문 항목의 수량은 0 이상이어야 한다`() {
            assertThatIllegalArgumentException()
                .isThrownBy {
                    order(listOf(orderLineItem(IdGenerator.createId(), BigDecimal.valueOf(4_500L), -1)))
                }
        }
    }

    @Nested
    @DisplayName("주문 접수")
    inner class OrderAccept {

        @Test
        fun `주문을 접수한다`() {
            val actual = order(
                "서울특별시 강남구 논현로 656",
                listOf(orderLineItem(IdGenerator.createId(), BigDecimal.valueOf(4_500L), 1))
            )
            actual.accept()

            assertThat(actual.status).isEqualTo(OrderStatus.ACCEPTED)
        }

        @EnumSource(value = OrderStatus::class, names = ["WAITING"], mode = EnumSource.Mode.EXCLUDE)
        @ParameterizedTest
        fun `접수 대기 중인 주문만 접수할 수 있다`(status: OrderStatus) {
            val actual = order(
                status,
                "서울특별시 강남구 논현로 656",
                listOf(orderLineItem(IdGenerator.createId(), BigDecimal.valueOf(4_500L), 1))
            )

            assertThatIllegalArgumentException()
                .isThrownBy {
                    actual.accept()
                }
        }
    }
}