package com.dailycoffee.menus.infra

import com.dailycoffee.RepositoryTest
import com.dailycoffee.menu
import com.dailycoffee.menuGroup
import com.dailycoffee.menuProduct
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

@DisplayName("메뉴 저장소 테스트")
@RepositoryTest
class JpaMenuRepositoryTest(
    private val menuRepository: JpaMenuRepository
) {
    @AfterEach
    internal fun tearDown() {
        menuRepository.deleteAll()
    }

    @Test
    fun `메뉴의 목록을 조회할 수 있다`() {
        val menuGroup = menuGroup("추천")
        menuRepository.save(
            menu(
                "아이스 카페 아메리카노",
                BigDecimal.valueOf(4_500L),
                true,
                menuGroup.id,
                listOf(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(4_500L), 1))
            )
        )
        menuRepository.save(
            menu(
                "아이스 카페라떼",
                BigDecimal.valueOf(5_000L),
                true,
                menuGroup.id,
                listOf(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(5_000L), 1))
            )
        )

        val actual = menuRepository.findAll()
        Assertions.assertThat(actual).hasSize(2)
    }
}
