package com.deepak.codeauditai.ai

import com.deepak.codeauditai.ai.model.Content
import com.deepak.codeauditai.ai.model.GeminiRequest
import com.deepak.codeauditai.ai.model.GeminiResponse
import com.deepak.codeauditai.ai.model.Part
import com.deepak.codeauditai.config.AppConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import io.ktor.client.call.body

class GeminiService(
    private val client: HttpClient
) {

    suspend fun analyseCode(code: String): String {
        val prompt = """
           You are a Senior Android engineer.
           
           Analyse the following Kotlin/Jetpack code.
           
           Provide: 
           - Code quality issues
           - Performance concerns
           - Best practice suggestions
           
           Code: 
           $code
       """.trimIndent()

        val httpResponse =
            client.post("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent")
            {
                parameter("key", AppConfig.geminiApiKey)
                println("API KEY -> ${AppConfig.geminiApiKey}")

                contentType(ContentType.Application.Json)

                setBody(
                    GeminiRequest(
                        contents = listOf(
                            Content(
                                parts = listOf(
                                    Part(prompt)
                                )
                            )
                        )
                    )
                )
            }

        val rawBody = httpResponse.body<String>()

        println(rawBody)

        val response = Json.decodeFromString<GeminiResponse>(rawBody)

        return response
            .candidates
            .firstOrNull()
            ?.content
            ?.parts
            ?.firstOrNull()
            ?.text
            ?: "No AI Response"
    }
}