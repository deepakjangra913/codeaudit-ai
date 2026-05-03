package com.deepak.codeauditai.data.repository

import com.deepak.codeauditai.domain.repository.CodeReviewRepository
import com.deepak.codeauditai.models.ReviewResult

class CodeReviewRepositoryImpl : CodeReviewRepository {

    override suspend fun reviewCode(code: String): ReviewResult {
        // TODO("Integrate Ai + rules")
        return ReviewResult(emptyList())
    }
}