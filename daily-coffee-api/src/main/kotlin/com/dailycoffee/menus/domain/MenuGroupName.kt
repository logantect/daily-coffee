package com.dailycoffee.menus.domain

data class MenuGroupName(
    private val name: String
) {
    init {
        require(name.isNotBlank())
    }
}
