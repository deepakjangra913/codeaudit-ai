package com.deepak.codeauditai

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.deepak.codeauditai.presentation.screen.CodeReviewScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        CodeReviewScreen()
    }
}