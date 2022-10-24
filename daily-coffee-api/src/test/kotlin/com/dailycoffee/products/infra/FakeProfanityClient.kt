package com.dailycoffee.products.infra

import com.dailycoffee.products.domain.ProfanityClient

class FakeProfanityClient : ProfanityClient {
    override fun containsProfanity(text: String): Boolean {
        return profanities.any { text.contains(it) }
    }

    companion object {
        private val profanities: List<String> = listOf("비속어", "욕설")
    }
}
