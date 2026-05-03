package com.deepak.codeauditai.domain.usecase

import com.deepak.codeauditai.domain.repository.CodeReviewRepository
import com.deepak.codeauditai.models.ReviewResult

class ReviewCodeUseCase(private val repository: CodeReviewRepository) {

    suspend operator fun invoke(code: String): ReviewResult {
        return repository.reviewCode(code)
    }
}