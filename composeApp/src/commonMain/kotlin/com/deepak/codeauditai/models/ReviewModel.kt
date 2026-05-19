package com.deepak.codeauditai.models

data class ReviewResult(
    val items: List<ReviewItem>
)

data class ReviewItem(
    val title: String,
    val description: String,
    val severity: Severity,
    val category: ReviewCategory
)

enum class Severity {
    CRITICAL,
    WARNING,
    SUGGESTION,
    INFO,
}