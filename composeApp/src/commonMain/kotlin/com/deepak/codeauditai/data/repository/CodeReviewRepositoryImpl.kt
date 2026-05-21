package com.deepak.codeauditai.data.repository

import com.deepak.codeauditai.ai.GeminiService
import com.deepak.codeauditai.ai.parser.GeminiResponseParser
import com.deepak.codeauditai.domain.repository.CodeReviewRepository
import com.deepak.codeauditai.models.ReviewCategory
import com.deepak.codeauditai.models.ReviewItem
import com.deepak.codeauditai.models.ReviewResult
import com.deepak.codeauditai.rules.RuleEngine

/**
 * Repository implementation responsible for combining
 * rule-based static analysis and AI-powered code review
 * into a unified review result.
 *
 * This repository acts as the central orchestration layer
 * between:
 * - local rule engine analysis
 * - Gemini AI analysis
 * - response parsing
 *
 * Workflow:
 * 1. Run local static rule analysis
 * 2. Request AI-based code review from Gemini
 * 3. Parse raw AI response into structured review items
 * 4. Merge all review results into a single domain model
 *
 * This hybrid approach enables:
 * - deterministic rule validation
 * - intelligent AI suggestions
 * - scalable review architecture
 *
 * @property ruleEngine Handles local rule-based code checks.
 *
 * @property geminiService Responsible for communicating
 * with Gemini AI APIs.
 *
 * @property geminiResponseParser Converts raw AI responses
 * into structured [ReviewItem] models.
 */
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