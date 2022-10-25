package com.dailycoffee.menus.domain

import com.dailycoffee.menu
import com.dailycoffee.menuGroup
import com.dailycoffee.menuProduct
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

internal class MenuTest {

    @Nested
    @DisplayName("메뉴 등록")
    inner class CreateMenu {
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
                assertThat(actual.price).isEqualTo(Price(4_500L))
                assertThat(actual.displayed).isTrue
                assertThat(actual.menuGroupId).isEqualTo(menuGroup.id)
                assertThat(actual.menuProducts.menuProducts).hasSize(1)
            }.assertAll()
        }

        @Test
        fun `상품이 없으면 등록할 수 없다`() {
            assertThatIllegalArgumentException()
                .isThrownBy {
                    val menuGroup = menuGroup("추천")
                    menu(
                        "아이스 카페 아메리카노",
                        BigDecimal.valueOf(4_500L),
                        true,
                        menuGroup.id,
                        emptyList()
                    )
                }
        }

        @Test
        fun `메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다`() {
            assertThatIllegalArgumentException()
                .isThrownBy {
                    val menuGroup = menuGroup("추천")
                    menu(
                        "아이스 카페 아메리카노 + 조각케익",
                        BigDecimal.valueOf(12_000L),
                        true,
                        menuGroup.id,
                        listOf(
                            menuProduct(UUID.randomUUID(), BigDecimal.valueOf(4_500L), 1),
                            menuProduct(UUID.randomUUID(), BigDecimal.valueOf(6_500L), 1)
                        )
                    )
                }
        }
    }

    @Nested
    @DisplayName("메뉴 가격 변경")
    inner class ChangeMenuPrice {
        @Test
        fun `메뉴의 가격을 변경할 수 있다`() {
            val menuGroup = menuGroup("추천")
            val actual = menu(
                "아이스 카페 아메리카노",
                BigDecimal.valueOf(4_500L),
                true,
                menuGroup.id,
                listOf(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(4_500L), 1))
            )
            val expected = Price(4_000L)
            actual.changePrice(expected)

            assertThat(actual.price).isEqualTo(expected)
        }

        @Test
        fun `메뉴에 속한 상품 금액의 합은 메뉴의 가격보다 크거나 같아야 한다`() {
            val menuGroup = menuGroup("추천")
            val actual = menu(
                "아이스 카페 아메리카노",
                BigDecimal.valueOf(4_500L),
                true,
                menuGroup.id,
                listOf(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(4_500L), 1))
            )

            assertThatIllegalArgumentException()
                .isThrownBy {
                    actual.changePrice(Price(4_600L))
                }
        }
    }

    @Nested
    @DisplayName("메뉴 공개")
    inner class DisplayMenu {
        @Test
        fun `메뉴를 공개할 수 있다`() {
            val menuGroup = menuGroup("추천")
            val actual = menu(
                "아이스 카페 아메리카노",
                BigDecimal.valueOf(4_500L),
                false,
                menuGroup.id,
                listOf(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(4_500L), 1))
            )
            actual.display()

            assertThat(actual.displayed).isTrue
        }

        @Test
        fun `메뉴의 가격이 메뉴에 속한 상품 금액의 합보다 높을 경우 메뉴를 공개할 수 없다`() {
            val menuGroup = menuGroup("추천")
            val productId = UUID.randomUUID()
            val actual = menu(
                "아이스 카페 아메리카노",
                BigDecimal.valueOf(4_500L),
                false,
                menuGroup.id,
                listOf(menuProduct(productId, BigDecimal.valueOf(4_500L), 1))
            )
            actual.removeMenuProduct(menuProduct(productId, BigDecimal.valueOf(4_500L), 1))
            actual.addMenuProduct(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(4_000L), 1))

            assertThatIllegalArgumentException()
                .isThrownBy {
                    actual.display()
                }
        }
    }
}
