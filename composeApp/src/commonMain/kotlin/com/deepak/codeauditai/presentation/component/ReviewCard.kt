package com.deepak.codeauditai.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.deepak.codeauditai.models.ReviewCategory
import com.deepak.codeauditai.models.ReviewItem
import com.deepak.codeauditai.models.Severity
import com.deepak.codeauditai.presentation.extensions.gradientBrush

/**
 * Displays a visually styled review card representing
 * a single AI or rule-based code analysis result.
 *
 * The card appearance dynamically changes based on
 * the severity level of the review item by applying
 * severity-specific gradient backgrounds.
 *
 * This composable is used to present:
 * - performance warnings
 * - code quality suggestions
 * - best practice recommendations
 * - architecture insights
 *
 * @param modifier Modifier used to decorate or
 * adjust the layout behavior of the card.
 *
 * @param reviewItem Contains the review metadata
 * including title, description, severity,
 * and review category.
 */
@Composable
fun ReviewCard(
    modifier: Modifier = Modifier,
    reviewItem: ReviewItem
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(size = 24.dp))
            .fillMaxWidth()
            .height(100.dp)
            .background(reviewItem.severity.gradientBrush())
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = reviewItem.title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun ReviewCardPreview() {
    MaterialTheme {
        ReviewCard(
            reviewItem = ReviewItem(
                title = "PERFORMANCE",
                description = "Performance can be improved",
                severity = Severity.WARNING,
                category = ReviewCategory.PERFORMANCE
            )
        )
    }
}