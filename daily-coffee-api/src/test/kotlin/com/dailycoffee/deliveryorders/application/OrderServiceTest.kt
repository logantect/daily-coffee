package com.dailycoffee.deliveryorders.application

import com.dailycoffee.deliveryorders.domain.OrderRepository
import com.dailycoffee.deliveryorders.domain.OrderValidator
import com.dailycoffee.deliveryorders.infra.InMemoryOrderRepository
import com.dailycoffee.menu
import com.dailycoffee.menuGroup
import com.dailycoffee.menuProduct
import com.dailycoffee.menus.domain.MenuGroupRepository
import com.dailycoffee.menus.domain.MenuRepository
import com.dailycoffee.menus.infra.InMemoryMenuGroupRepository
import com.dailycoffee.menus.infra.InMemoryMenuRepository
import com.dailycoffee.product
import com.dailycoffee.products.domain.DisplayedName
import com.dailycoffee.products.domain.ProductRepository
import com.dailycoffee.products.infra.FakeProfanityClient
import com.dailycoffee.products.infra.InMemoryProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

@DisplayName("주문 서비스 테스트")
class OrderServiceTest {
    private lateinit var orderService: OrderService
    private lateinit var orderRepository: OrderRepository
    private lateinit var productRepository: ProductRepository
    private lateinit var menuGroupRepository: MenuGroupRepository
    private lateinit var menuRepository: MenuRepository
    private lateinit var profanityClient: FakeProfanityClient

    @BeforeEach
    internal fun setUp() {
        orderRepository = InMemoryOrderRepository()
        productRepository = InMemoryProductRepository()
        menuGroupRepository = InMemoryMenuGroupRepository()
        menuRepository = InMemoryMenuRepository()
        profanityClient = FakeProfanityClient()
        orderService = OrderService(orderRepository, OrderValidator(productRepository, menuRepository))
    }

    @AfterEach
    internal fun tearDown() {
        orderRepository.deleteAll()
    }

    @Nested
    @DisplayName("주문 등록")
    inner class OrderCreate {

        @Test
        fun `1개 이상의 등록된 메뉴로 배달 주문을 등록할 수 있다`() {
            val product = productRepository.save(product(DisplayedName("아이스 아메리카노", profanityClient), 5_800L))
            val menuGroup = menuGroupRepository.save(menuGroup("추천"))
            val menu = menuRepository.save(
                menu(
                    "아이스 카페 아메리카노",
                    BigDecimal.valueOf(4_500L),
                    true,
                    menuGroup.id,
                    listOf(menuProduct(product.id, BigDecimal.valueOf(5_800L), 1))
                )
            )

            val orderRequest = OrderRequest(
                deliveryAddress = "서울특별시 강남구 논현로 656",
                orderLineItems = listOf(
                    OrderLineItemsRequest(
                        menuId = menu.id,
                        price = BigDecimal.valueOf(5_800L),
                        quantity = 1
                    )
                )
            )

            val actual = orderService.create(orderRequest)
            assertThat(actual).isNotNull
            SoftAssertions().apply {
                assertThat(actual.id).isNotNull
                assertThat(actual.orderLineItems).hasSize(1)
            }.assertAll()
        }

        @Test
        fun `메뉴가 없으면 등록할 수 없다`() {
            val orderRequest = OrderRequest(
                deliveryAddress = "서울특별시 강남구 논현로 656",
                orderLineItems = listOf()
            )
            assertThatIllegalArgumentException().isThrownBy {
                orderService.create(orderRequest)
            }
        }

        @Test
        fun `메뉴가 존재하지 않으면 등록할 수 없다`() {
            val orderRequest = OrderRequest(
                deliveryAddress = "서울특별시 강남구 논현로 656",
                orderLineItems = listOf(
                    OrderLineItemsRequest(
                        menuId = UUID(0L, 0L),
                        price = BigDecimal.valueOf(5_800L),
                        quantity = 1
                    )
                )
            )
            assertThatIllegalArgumentException().isThrownBy {
                orderService.create(orderRequest)
            }
        }
    }
}
