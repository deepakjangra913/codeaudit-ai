package com.deepak.codeauditai.data.repository

import com.deepak.codeauditai.domain.repository.CodeReviewRepository
import com.deepak.codeauditai.models.ReviewItem
import com.deepak.codeauditai.models.ReviewResult
import com.deepak.codeauditai.rules.RuleEngine

class CodeReviewRepositoryImpl : CodeReviewRepository {

    private val ruleEngine = RuleEngine()

    override suspend fun reviewCode(code: String): ReviewResult {

        val ruleResults = ruleEngine.analyze(code)

        val items = ruleResults.map {
            ReviewItem(
                title = "Code Issue",
                description = it.message,
                severity = it.severity
            )
        }

        return ReviewResult(items)
    }
}