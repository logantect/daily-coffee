package com.dailycoffee.products.interfaces

import com.dailycoffee.products.application.CreateProductRequest
import com.dailycoffee.products.domain.ProductRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import java.math.BigDecimal

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class ProductRestControllerTest(
    @LocalServerPort private val port: Int,
    private val productRepository: ProductRepository,
) {
    @BeforeEach
    internal fun setUp() {
        RestAssured.port = port
    }

    @AfterEach
    internal fun tearDown() {
        RestAssured.reset()
        productRepository.deleteAll()
    }

    @Test
    fun `상품 등록 요청에 응답으로 201 Created와 생성된 상품을 반환한다`() {
        val request = CreateProductRequest(
            name = "아이스 카페 아메리카노",
            price = BigDecimal.valueOf(4_500L),
        )

        Given {
            contentType(ContentType.JSON)
            body(request)
            log().all()
        } When {
            post("/api/v1/products")
        } Then {
            statusCode(HttpStatus.SC_CREATED)
            body("name", equalTo("아이스 카페 아메리카노"))
            body("price", equalTo(4_500))
            log().all()
        }
    }

    @Test
    fun `상품 가격 변경 요청에 응답으로 200 OK를 반환한다`() {
        val request = CreateProductRequest(
            name = "아이스 카페 아메리카노",
            price = BigDecimal.valueOf(4_500L),
        )

        Given {
            contentType(ContentType.JSON)
            body(request)
            log().all()
        } When {
            post("/api/v1/products")
        } Then {
            statusCode(HttpStatus.SC_OK)
            log().all()
        }
    }
}
