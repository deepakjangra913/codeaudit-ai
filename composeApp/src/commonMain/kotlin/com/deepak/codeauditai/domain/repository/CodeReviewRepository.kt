package com.deepak.codeauditai.domain.repository

import com.deepak.codeauditai.models.ReviewResult

interface CodeReviewRepository {

    suspend fun reviewCode(code: String): ReviewResult
}