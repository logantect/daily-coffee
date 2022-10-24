package com.dailycoffee.menus.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

internal class MenuGroupNameTest {

    @Test
    fun `메뉴그룹 이름을 생성할 수 있다`() {
        val actual = MenuGroupName("에스프레소")
        assertThat(actual).isEqualTo(MenuGroupName("에스프레소"))
    }

    @EmptySource
    @ParameterizedTest
    fun `메뉴 그룹의 이름이 올바르지 않으면 등록할 수 없다`(name: String) {
        assertThatIllegalArgumentException()
            .isThrownBy { MenuGroupName(name) }
    }
}
