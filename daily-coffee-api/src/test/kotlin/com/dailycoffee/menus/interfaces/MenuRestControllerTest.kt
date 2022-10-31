package com.dailycoffee.menus.interfaces

import com.dailycoffee.menuProductRequest
import com.dailycoffee.menuRequest
import com.dailycoffee.menus.domain.MenuRepository
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
class MenuRestControllerTest(
    @LocalServerPort private val port: Int,
    private val menuRepository: MenuRepository
) {
    @BeforeEach
    internal fun setUp() {
        RestAssured.port = port
    }

    @AfterEach
    internal fun tearDown() {
        RestAssured.reset()
        menuRepository.deleteAll()
    }

    @Test
    fun `메뉴 등록 요청에 응답으로 201 Created와 생성된 메뉴를 반환한다`() {
        val menuGroupId = UUID.randomUUID()
        val request = menuRequest(
            "아이스 카페 아메리카노",
            BigDecimal.valueOf(4_500L),
            true,
            menuGroupId,
            listOf(menuProductRequest(UUID.randomUUID(), BigDecimal.valueOf(4_500L), 1L))
        )

        Given {
            contentType(ContentType.JSON)
            body(request)
            log().all()
        } When {
            post("/api/v1/menus")
        } Then {
            statusCode(HttpStatus.SC_CREATED)
            body("id", notNullValue())
            body("name", equalTo("아이스 카페 아메리카노"))
            body("price", equalTo(4_500))
            body("displayed", `is`(true))
            body("menuGroupId", equalTo(menuGroupId.toString()))
            body("menuProducts.size()", `is`(1))
            log().all()
        }
    }
}
