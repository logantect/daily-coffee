package com.dailycoffee.products.domain

data class DisplayedName(
    private val name: String,
    private val profanityClient: ProfanityClient
) {
    init {
        require(!profanityClient.containsProfanity(name))
    }
}
