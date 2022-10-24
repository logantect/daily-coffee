package com.dailycoffee.menus.domain

import com.dailycoffee.menu
import com.dailycoffee.menuGroup
import com.dailycoffee.menuProduct
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

internal class MenuTest {

    @Test
    fun `1개 이상의 등록된 상품으로 메뉴를 등록할 수 있다`() {
        val menuGroup = menuGroup("추천")
        val actual = menu(
            "아이스 카페 아메리카노",
            BigDecimal.valueOf(4_500L),
            true,
            menuGroup.id,
            listOf(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(4_500L), 1))
        )

        assertThat(actual).isNotNull
        SoftAssertions().apply {
            assertThat(actual.id).isNotNull
            assertThat(actual.name).isEqualTo("아이스 카페 아메리카노")
            assertThat(actual.price).isEqualTo(BigDecimal.valueOf(4_500L))
            assertThat(actual.displayed).isTrue
            assertThat(actual.menuGroupId).isEqualTo(menuGroup.id)
            assertThat(actual.menuProducts).hasSize(1)
        }.assertAll()
    }
}
