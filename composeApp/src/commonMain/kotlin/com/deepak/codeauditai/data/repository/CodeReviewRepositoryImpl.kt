package com.deepak.codeauditai.data.repository

import com.deepak.codeauditai.ai.GeminiService
import com.deepak.codeauditai.ai.parser.GeminiResponseParser
import com.deepak.codeauditai.domain.repository.CodeReviewRepository
import com.deepak.codeauditai.models.ReviewCategory
import com.deepak.codeauditai.models.ReviewItem
import com.deepak.codeauditai.models.ReviewResult
import com.deepak.codeauditai.rules.RuleEngine

class CodeReviewRepositoryImpl(
    private val ruleEngine: RuleEngine,
    private val geminiService: GeminiService,
    private val geminiResponseParser: GeminiResponseParser
) : CodeReviewRepository {

    override suspend fun reviewCode(code: String): ReviewResult {

        // Rule-based analysis
        val ruleResults = ruleEngine.analyze(code)

        val ruleItems = ruleResults.map {
            ReviewItem(
                title = "Code Issue",
                description = it.message,
                severity = it.severity,
                category = ReviewCategory.CODE_QUALITY
            )
        }

        // AI-based analysis
        val aiResponse = geminiService.analyseCode(code)
        val aiItems = geminiResponseParser.parse(aiResponse)

        // Combine results
        return ReviewResult(
            items = ruleItems + aiItems
        )
    }
}