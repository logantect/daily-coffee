package com.dailycoffee.menus.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MenuGroupTest {

    @Test
    fun `메뉴 그룹을 등록할 수 있다`() {
        val actual = MenuGroup("추천")

        Assertions.assertThat(actual).isNotNull
        assertAll(
            { Assertions.assertThat(actual.id).isNotNull },
            { Assertions.assertThat(actual.name).isEqualTo("추천") },
        )
    }
}
