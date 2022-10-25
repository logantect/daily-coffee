package com.dailycoffee.menus.domain

import com.dailycoffee.menuGroup
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("메뉴 그룹 테스트")
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
