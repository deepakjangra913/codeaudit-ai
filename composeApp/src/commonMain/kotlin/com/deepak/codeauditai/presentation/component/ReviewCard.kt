package com.deepak.codeauditai.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.deepak.codeauditai.models.ReviewItem

@Composable
fun ReviewCard(
    modifier: Modifier = Modifier,
    reviewItem: ReviewItem? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()

    ) {

    }
}

@Preview
@Composable
fun ReviewCardPreview() {
    MaterialTheme {
        ReviewCard()
    }
}