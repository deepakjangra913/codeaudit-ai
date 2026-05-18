package com.deepak.codeauditai.ai.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate> = emptyList()
)

@Serializable
data class Candidate(
    val content: GeminiContent
)

@Serializable
data class GeminiContent(
    val parts: List<GeminiPart>
)

@Serializable
data class GeminiPart(
    val text: String
)