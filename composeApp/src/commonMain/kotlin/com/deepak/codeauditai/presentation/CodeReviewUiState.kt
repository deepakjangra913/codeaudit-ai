package com.deepak.codeauditai.presentation

import com.deepak.codeauditai.models.ReviewItem

data class CodeReviewUiState(
    val code: String = "",
    val isLoading: Boolean = false,
    val result: List<ReviewItem> = emptyList(),
    val error: String? = null
)
