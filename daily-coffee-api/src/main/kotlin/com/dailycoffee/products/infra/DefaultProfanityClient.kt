package com.dailycoffee.products.infra

import com.dailycoffee.products.domain.ProfanityClient
import org.springframework.stereotype.Component

// TODO 웹 플럭스 연동하여 https://www.purgomalum.com/service/containsprofanity API 호출로 개선 필요
@Component
class DefaultProfanityClient : ProfanityClient {
    override fun containsProfanity(text: String): Boolean {
        return profanities.any { text.contains(it) }
    }

    companion object {
        private val profanities: List<String> = listOf("비속어", "욕설")
    }
}
