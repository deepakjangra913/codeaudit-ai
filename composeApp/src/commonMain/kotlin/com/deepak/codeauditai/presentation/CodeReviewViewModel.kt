package com.deepak.codeauditai.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepak.codeauditai.domain.usecase.ReviewCodeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing the UI state
 * and orchestration logic for the code review screen.
 *
 * This ViewModel handles:
 * - code input updates
 * - triggering AI/code analysis
 * - loading state management
 * - error handling
 * - exposing structured review results to the UI
 *
 * The ViewModel follows a unidirectional state flow
 * approach using [StateFlow] to provide reactive and
 * lifecycle-aware UI updates.
 *
 * Workflow:
 * 1. User pastes Kotlin/Compose code
 * 2. UI updates the current code state
 * 3. Analysis request is triggered
 * 4. Use case performs rule-based + AI review
 * 5. UI state updates with results or errors
 *
 * @property useCase Handles the business logic for
 * reviewing source code and generating review results.
 */

class CodeReviewViewModel(private val useCase: ReviewCodeUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(CodeReviewUiState())
    val uiState = _uiState.asStateFlow()

    fun onCodeChange(code: String) {
        _uiState.update {
            it.copy(code = code)
        }
    }

    fun analyseCode() = viewModelScope.launch(Dispatchers.IO) {
        val currentCode = _uiState.value.code
        if (currentCode.isEmpty()) {
            return@launch
        }

        _uiState.update {
            it.copy(isLoading = true, error = null)
        }

        try {
            val result = useCase.invoke(code = currentCode)
            _uiState.update {
                it.copy(
                    isLoading = false,
                    result = result.items
                )
            }
        } catch (exception: Exception) {
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = exception.message
                )
            }
        }
    }
}