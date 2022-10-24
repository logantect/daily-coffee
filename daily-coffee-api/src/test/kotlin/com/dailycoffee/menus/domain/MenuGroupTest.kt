package com.dailycoffee.menus.domain

import com.dailycoffee.menuGroup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class MenuGroupTest {

    @Test
    fun `메뉴 그룹을 등록할 수 있다`() {
        val actual = menuGroup("추천")

        assertThat(actual).isNotNull
        assertAll(
            { assertThat(actual.id).isNotNull },
            { assertThat(actual.name).isEqualTo(MenuGroupName("추천")) },
        )
    }
}
