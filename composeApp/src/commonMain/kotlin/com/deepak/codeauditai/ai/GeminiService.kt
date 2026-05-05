package com.deepak.codeauditai.ai

import io.ktor.client.HttpClient

class GeminiService (
    private val client: HttpClient
){

    suspend fun analyseCode(code: String): String {
        // TODO (Call gemini api)
        return "AI response"
    }
}