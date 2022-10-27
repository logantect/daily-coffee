package com.dailycoffee.menus.infra

import com.dailycoffee.menuGroup
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor

@DataJpaTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class JpaMenuGroupRepositoryTest(
    private val menuGroupRepository: JpaMenuGroupRepository
) {
    @AfterEach
    internal fun tearDown() {
        menuGroupRepository.deleteAll()
    }

    @Test
    fun `메뉴그룹의 목록을 조회할 수 있다`() {
        menuGroupRepository.save(menuGroup("추천"))
        menuGroupRepository.save(menuGroup("에스프레소"))

        val actual = menuGroupRepository.findAll()
        Assertions.assertThat(actual).hasSize(2)
    }
}
