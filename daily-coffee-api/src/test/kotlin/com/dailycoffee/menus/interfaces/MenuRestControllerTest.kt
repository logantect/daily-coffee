package com.dailycoffee.menus.interfaces

import com.dailycoffee.menu
import com.dailycoffee.menuGroup
import com.dailycoffee.menuProduct
import com.dailycoffee.menuProductRequest
import com.dailycoffee.menuRequest
import com.dailycoffee.menus.application.ChangePriceRequest
import com.dailycoffee.menus.domain.MenuRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers
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

    @Test
    fun `메뉴 가격 변경 요청에 응답으로 200 OK를 반환한다`() {
        val menuGroup = menuGroup("추천")
        val menu = menuRepository.save(
            menu(
                "아이스 카페 아메리카노",
                BigDecimal.valueOf(4_500L),
                true,
                menuGroup.id,
                listOf(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(4_500L), 1))
            )
        )

        val request = ChangePriceRequest(price = BigDecimal.valueOf(4_000L))

        Given {
            contentType(ContentType.JSON)
            body(request)
            log().all()
        } When {
            patch("/api/v1/menus/{menuId}/price", menu.id)
        } Then {
            statusCode(HttpStatus.SC_OK)
            log().all()
        }
    }

    @Test
    fun `메뉴 목록 조회 요청으로 200 OK와 메뉴 목록을 반환한다`() {
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
                BigDecimal.valueOf(5_500L),
                true,
                menuGroup.id,
                listOf(menuProduct(UUID.randomUUID(), BigDecimal.valueOf(5_500L), 1))
            )
        )

        Given {
            contentType(ContentType.JSON)
            log().all()
        } When {
            get("/api/v1/menus")
        } Then {
            statusCode(HttpStatus.SC_OK)
            body("data.size()", CoreMatchers.`is`(2))
            log().all()
        }
    }
}
