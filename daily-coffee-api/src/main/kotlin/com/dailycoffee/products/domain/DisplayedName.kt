package com.dailycoffee.products.domain

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class DisplayedName(
    @Column(nullable = false) val name: String,
    @field:Transient private val profanityClient: ProfanityClient
) {
    init {
        require(!profanityClient.containsProfanity(name))
    }
}
