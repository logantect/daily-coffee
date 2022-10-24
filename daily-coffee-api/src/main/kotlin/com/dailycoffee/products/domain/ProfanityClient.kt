package com.dailycoffee.products.domain

interface ProfanityClient {
    fun containsProfanity(text: String): Boolean
}
