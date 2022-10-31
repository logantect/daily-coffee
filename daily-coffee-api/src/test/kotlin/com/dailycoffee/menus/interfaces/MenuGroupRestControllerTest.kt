package com.dailycoffee.menus.interfaces

import com.dailycoffee.menuGroup
import com.dailycoffee.menus.application.CreateMenuGroupRequest
import com.dailycoffee.menus.domain.MenuGroupRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class MenuGroupRestControllerTest(
    @LocalServerPort private val port: Int,
    private val menuGroupRepository: MenuGroupRepository
) {
    @BeforeEach
    internal fun setUp() {
        RestAssured.port = port
    }

    @AfterEach
    internal fun tearDown() {
        RestAssured.reset()
        menuGroupRepository.deleteAll()
    }

    @Test
    fun `메뉴 그룹 등록 요청에 응답으로 201 Created와 생성된 메뉴그룹을 반환한다`() {
        val request = CreateMenuGroupRequest(
            name = "추천",
        )

        Given {
            contentType(ContentType.JSON)
            body(request)
            log().all()
        } When {
            post("/api/v1/menu-groups")
        } Then {
            statusCode(HttpStatus.SC_CREATED)
            body("id", notNullValue())
            body("name", equalTo("추천"))
            log().all()
        }
    }

    @Test
    fun `메뉴 그룹의 목록을 조회 요청으로 200 OK와 메뉴 그룹 목록을 반환한다`() {
        menuGroupRepository.save(menuGroup("추천"))
        menuGroupRepository.save(menuGroup("디카페인 커피"))

        Given {
            contentType(ContentType.JSON)
            log().all()
        } When {
            get("/api/v1/menu-groups")
        } Then {
            statusCode(HttpStatus.SC_OK)
            body("data.size()", CoreMatchers.`is`(2))
            log().all()
        }
    }
}
