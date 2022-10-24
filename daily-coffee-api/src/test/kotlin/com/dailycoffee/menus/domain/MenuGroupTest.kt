package com.dailycoffee.menus.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

internal class MenuGroupTest {

    @Test
    fun `메뉴 그룹을 등록할 수 있다`() {
        val actual = MenuGroup("추천")

        assertThat(actual).isNotNull
        assertAll(
            { assertThat(actual.id).isNotNull },
            { assertThat(actual.name).isEqualTo("추천") },
        )
    }

    @EmptySource
    @ParameterizedTest
    fun `메뉴 그룹의 이름이 올바르지 않으면 등록할 수 없다`(name: String) {
        assertThatIllegalArgumentException()
            .isThrownBy { MenuGroup(name) }
    }
}
