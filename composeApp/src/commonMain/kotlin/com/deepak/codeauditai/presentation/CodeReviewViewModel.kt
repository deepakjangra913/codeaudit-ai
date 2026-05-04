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