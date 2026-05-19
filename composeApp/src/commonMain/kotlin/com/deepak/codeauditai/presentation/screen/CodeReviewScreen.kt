package com.deepak.codeauditai.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.deepak.codeauditai.ai.GeminiService
import com.deepak.codeauditai.ai.parser.GeminiResponseParser
import com.deepak.codeauditai.data.repository.CodeReviewRepositoryImpl
import com.deepak.codeauditai.domain.usecase.ReviewCodeUseCase
import com.deepak.codeauditai.network.createHttpClient
import com.deepak.codeauditai.presentation.CodeReviewViewModel
import com.deepak.codeauditai.rules.RuleEngine

@Preview
@Composable
fun CodeReviewScreen() {

    val viewModel = remember {
        val client = createHttpClient()
        val geminiService = GeminiService(client)
        val geminiResponseParser = GeminiResponseParser()
        val repository = CodeReviewRepositoryImpl(
            ruleEngine = RuleEngine(),
            geminiService = geminiService,
            geminiResponseParser = geminiResponseParser
        )
        val useCase = ReviewCodeUseCase(repository)
        CodeReviewViewModel(useCase)
    }

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(horizontal = 16.dp),
            value = state.code,
            placeholder = {
                Text(
                    text = "Paste your code here..."
                )
            },
            onValueChange = viewModel::onCodeChange
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.analyseCode()
            }
        ) {
            Text("Analyze")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 500.dp)
        ) {

            items(state.result) { item ->

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = item.description)
                }
            }
        }
    }
}
