package com.dailycoffee.deliveryorders.infra

import com.dailycoffee.RepositoryTest
import com.dailycoffee.order
import com.dailycoffee.orderLineItem
import com.dailycoffee.support.utils.IdGenerator
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@DisplayName("주문 저장소 테스트")
@RepositoryTest
class JpaOrderRepositoryTest(
    private val orderRepository: JpaOrderRepository
) {
    @AfterEach
    internal fun tearDown() {
        orderRepository.deleteAll()
    }

    @Test
    fun `1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다`() {
        val actual = orderRepository.save(
            order(
                "서울특별시 강남구 논현로 656",
                listOf(orderLineItem(IdGenerator.createId(), BigDecimal.valueOf(4_500L), 1))
            )
        )

        assertThat(actual).isNotNull
        SoftAssertions().apply {
            assertThat(actual.id).isNotNull
            assertThat(actual.orderLineItems.orderLineItems).hasSize(1)
        }.assertAll()
    }
}
