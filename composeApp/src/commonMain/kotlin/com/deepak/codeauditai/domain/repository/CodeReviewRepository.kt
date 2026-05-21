package com.deepak.codeauditai.domain.repository

import com.deepak.codeauditai.models.ReviewResult

/**
 * Contract defining the code review operations exposed
 * by the domain layer.
 *
 * This repository abstracts the underlying implementation
 * details of:
 * - rule-based static analysis
 * - AI-powered code analysis
 * - response parsing
 *
 * The presentation and domain layers interact with this
 * contract without knowing how review data is generated.
 *
 * Implementations are responsible for:
 * - analyzing Kotlin/Compose code
 * - generating structured review results
 * - handling AI integrations
 *
 * @param code Raw Kotlin or Jetpack Compose source code
 * to be analyzed.
 *
 * @return [ReviewResult] containing structured review
 * items generated from static rules and AI analysis.
 */
interface CodeReviewRepository {

    suspend fun reviewCode(code: String): ReviewResult
}