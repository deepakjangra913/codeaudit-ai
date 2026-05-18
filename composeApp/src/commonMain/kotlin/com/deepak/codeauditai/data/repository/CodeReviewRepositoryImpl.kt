package com.deepak.codeauditai.data.repository

import com.deepak.codeauditai.ai.GeminiService
import com.deepak.codeauditai.domain.repository.CodeReviewRepository
import com.deepak.codeauditai.models.ReviewItem
import com.deepak.codeauditai.models.ReviewResult
import com.deepak.codeauditai.models.Severity
import com.deepak.codeauditai.rules.RuleEngine

class CodeReviewRepositoryImpl(
    private val ruleEngine: RuleEngine,
    private val geminiService: GeminiService
) : CodeReviewRepository {

    override suspend fun reviewCode(code: String): ReviewResult {

        // Rule-based analysis
        val ruleResults = ruleEngine.analyze(code)

        val ruleItems = ruleResults.map {
            ReviewItem(
                title = "Code Issue",
                description = it.message,
                severity = it.severity
            )
        }

        // AI-based analysis
        val aiResponse = geminiService.analyseCode(code)

        val aiItem = ReviewItem(
            title = "AI Suggestion",
            description = aiResponse,
            severity = Severity.SUGGESTION
        )

        // Combine results
        return ReviewResult(
            items = ruleItems + aiItem
        )
    }
}