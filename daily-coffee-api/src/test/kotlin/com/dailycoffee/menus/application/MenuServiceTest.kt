package com.dailycoffee.menus.application

import com.dailycoffee.menus.domain.MenuRepository
import com.dailycoffee.menus.infra.InMemoryMenuRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

@DisplayName("메뉴 서비스 테스트")
class MenuServiceTest {
    private lateinit var menuService: MenuService
    private lateinit var menuRepository: MenuRepository

    @BeforeEach
    fun setUp() {
        menuRepository = InMemoryMenuRepository()
        menuService = MenuService(menuRepository)
    }

    @AfterEach
    internal fun tearDown() {
        menuRepository.deleteAll()
    }

    @Nested
    @DisplayName("메뉴 등록")
    inner class ProductCreate {

        @Test
        fun `1개 이상의 등록된 상품으로 메뉴를 등록할 수 있다`() {
            val menuGroupId = UUID.randomUUID()
            val createMenuRequest = CreateMenuRequest(
                "아이스 카페 아메리카노",
                BigDecimal.valueOf(4_500L),
                true,
                menuGroupId,
                listOf(
                    MenuProductRequest(
                        productId = UUID.randomUUID(),
                        price = BigDecimal.valueOf(4_500L),
                        quantity = 1L,
                    )
                )
            )
            val actual = menuService.create(createMenuRequest)
            assertThat(actual.id).isNotNull
            assertThat(actual.name).isEqualTo("아이스 카페 아메리카노")
            assertThat(actual.price).isEqualTo(BigDecimal.valueOf(4_500L))
            assertThat(actual.displayed).isTrue
            assertThat(actual.menuGroupId).isEqualTo(menuGroupId)
            assertThat(actual.menuProducts).hasSize(1)
        }
    }
}
