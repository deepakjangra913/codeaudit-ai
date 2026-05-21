package com.deepak.codeauditai.presentation

import com.deepak.codeauditai.models.ReviewItem

/**
 * Represents the complete UI state for the
 * code review screen.
 *
 * This state model is observed by the UI layer
 * to render:
 * - user-entered source code
 * - loading indicators
 * - analysis results
 * - error states
 *
 * The state follows an immutable UI state pattern
 * commonly used in modern Compose architectures
 * such as:
 * - MVVM
 * - MVI
 * - unidirectional data flow
 *
 * @property code Current Kotlin/Compose source code
 * entered by the user for analysis.
 *
 * @property isLoading Indicates whether code analysis
 * is currently in progress.
 *
 * @property result Structured list of review items
 * generated from rule-based and AI-powered analysis.
 *
 * @property error Optional error message displayed
 * when analysis fails due to network, parsing,
 * or API-related issues.
 */
data class CodeReviewUiState(
    val code: String = "",
    val isLoading: Boolean = false,
    val result: List<ReviewItem> = emptyList(),
    val error: String? = null
)
