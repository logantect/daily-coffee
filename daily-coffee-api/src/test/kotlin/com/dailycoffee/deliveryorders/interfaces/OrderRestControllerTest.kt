package com.dailycoffee.deliveryorders.interfaces

import com.dailycoffee.IntegrationTest
import com.dailycoffee.deliveryorders.domain.OrderRepository
import com.dailycoffee.deliveryorders.domain.OrderStatus
import com.dailycoffee.menu
import com.dailycoffee.menuGroup
import com.dailycoffee.menuProduct
import com.dailycoffee.menus.domain.MenuGroupRepository
import com.dailycoffee.menus.domain.MenuRepository
import com.dailycoffee.orderLineItemsRequest
import com.dailycoffee.orderRequest
import com.dailycoffee.product
import com.dailycoffee.products.domain.DisplayedName
import com.dailycoffee.products.domain.ProductRepository
import com.dailycoffee.products.domain.ProfanityClient
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.web.server.LocalServerPort
import java.math.BigDecimal

@DisplayName("주문 통합 테스트")
@IntegrationTest
class OrderRestControllerTest(
    @LocalServerPort private val port: Int,
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val menuGroupRepository: MenuGroupRepository,
    private val menuRepository: MenuRepository,
    private val profanityClient: ProfanityClient,
) {
    @BeforeEach
    internal fun setUp() {
        RestAssured.port = port
    }

    @AfterEach
    internal fun tearDown() {
        RestAssured.reset()
        orderRepository.deleteAll()
        menuGroupRepository.deleteAll()
        menuRepository.deleteAll()
        productRepository.deleteAll()
    }

    @Nested
    @DisplayName("1개 이상의 등록된 메뉴로")
    inner class OrderCreate {

        @Test
        fun `주문 요청을 진행하면 응답으로 201 Created와 생성된 주문을 반환한다`() {
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

            val request = orderRequest(
                "서울특별시 강남구 논현로 656",
                listOf(
                    orderLineItemsRequest(menu.id, BigDecimal.valueOf(4_500L), 1)
                )
            )

            Given {
                contentType(ContentType.JSON)
                body(request)
                log().all()
            } When {
                post("/api/v1/orders")
            } Then {
                statusCode(HttpStatus.SC_CREATED)
                body("id", notNullValue())
                body("status", equalTo(OrderStatus.WAITING.name))
                body("orderDateTime", notNullValue())
                body("deliveryAddress", equalTo("서울특별시 강남구 논현로 656"))
                body("orderLineItems.size()", `is`(1))
                log().all()
            }
        }
    }
}
