package com.dailycoffee.deliveryorders.interfaces

import com.dailycoffee.deliveryorders.domain.OrderRepository
import com.dailycoffee.deliveryorders.domain.OrderStatus
import com.dailycoffee.orderLineItemsRequest
import com.dailycoffee.orderRequest
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
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import java.math.BigDecimal
import java.util.UUID

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OrderRestControllerTest(
    @LocalServerPort private val port: Int,
    private val orderRepository: OrderRepository
) {
    @BeforeEach
    internal fun setUp() {
        RestAssured.port = port
    }

    @AfterEach
    internal fun tearDown() {
        RestAssured.reset()
        orderRepository.deleteAll()
    }

    @Test
    fun `주문 요청에 응답으로 201 Created와 생성된 주문을 반환한다`() {
        val menuId = UUID.randomUUID()
        val request = orderRequest(
            "서울특별시 강남구 논현로 656",
            listOf(
                orderLineItemsRequest(menuId, BigDecimal.valueOf(4_500L), 1)
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
