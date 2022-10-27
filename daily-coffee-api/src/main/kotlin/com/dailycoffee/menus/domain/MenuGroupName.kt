package com.dailycoffee.menus.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class MenuGroupName(
    @Column(nullable = false) private val name: String
) {
    init {
        require(name.isNotBlank())
    }
}
