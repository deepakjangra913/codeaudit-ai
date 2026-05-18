package com.deepak.codeauditai.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deepak.codeauditai.ai.GeminiService
import com.deepak.codeauditai.data.repository.CodeReviewRepositoryImpl
import com.deepak.codeauditai.domain.usecase.ReviewCodeUseCase
import com.deepak.codeauditai.models.Severity
import com.deepak.codeauditai.network.createHttpClient
import com.deepak.codeauditai.rules.RuleEngine

@Preview
@Composable
fun CodeReviewScreen() {

    val viewModel = remember {
        val client = createHttpClient()
        val geminiService = GeminiService(client)
        val repository = CodeReviewRepositoryImpl(
            ruleEngine = RuleEngine(),
            geminiService = geminiService
        )
        val useCase = ReviewCodeUseCase(repository)
        CodeReviewViewModel(useCase)
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = state.code,
                placeholder = {
                    Text(
                        text = "Paste your code here..."
                    )
                },
                onValueChange = viewModel::onCodeChange
            )

            Column {
                state.result.forEach { item ->

                    val color = when (item.severity) {
                        Severity.CRITICAL -> Color.Red
                        Severity.WARNING -> Color.Yellow
                        Severity.SUGGESTION -> Color.Gray
                    }

                    Text(
                        text = "• ${item.description}",
                        color = color,
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }

                state.error?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = state.code.isNotEmpty(),
                onClick = {
                    viewModel.analyseCode()
                }
            ) {
                Text(
                    text = "Analyse Code"
                )
            }
        }
    }
}
