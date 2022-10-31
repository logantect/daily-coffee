package com.dailycoffee.menus.application

import com.dailycoffee.menuGroup
import com.dailycoffee.menus.domain.MenuGroupRepository
import com.dailycoffee.menus.infra.InMemoryMenuGroupRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.groups.Tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("메뉴 그룹 서비스 테스트")
class MenuGroupServiceTest {
    private lateinit var menuGroupService: MenuGroupService
    private lateinit var menuGroupRepository: MenuGroupRepository

    @BeforeEach
    fun setUp() {
        menuGroupRepository = InMemoryMenuGroupRepository()
        menuGroupService = MenuGroupService(menuGroupRepository)
    }

    @AfterEach
    internal fun tearDown() {
        menuGroupRepository.deleteAll()
    }

    @Nested
    @DisplayName("메뉴 그룹 등록")
    inner class MenuGroupCreate {

        @Test
        fun `메뉴 그룹을 등록할 수 있다`() {
            val actual = menuGroupService.create(
                CreateMenuGroupRequest(
                    name = "추천"
                )
            )

            assertThat(actual.id).isNotNull
            assertThat(actual.name).isEqualTo("추천")
        }
    }

    @Nested
    @DisplayName("메뉴 그룹 조회")
    inner class MenuGroupRetrieve {

        @Test
        fun `메뉴 그룹의 목록을 조회할 수 있다`() {
            menuGroupRepository.save(menuGroup("추천"))
            menuGroupRepository.save(menuGroup("디카페인 커피"))

            val actual = menuGroupService.findAll()

            assertThat(actual).map(MenuGroupResponse::name)
                .contains(Tuple.tuple("추천"), Tuple.tuple("디카페인 커피"))
        }
    }
}
