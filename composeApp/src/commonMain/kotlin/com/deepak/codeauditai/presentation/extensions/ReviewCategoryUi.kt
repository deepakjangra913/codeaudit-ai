package com.deepak.codeauditai.presentation.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Speed
import androidx.compose.ui.graphics.vector.ImageVector
import com.deepak.codeauditai.models.ReviewCategory

fun ReviewCategory.icon() : ImageVector {
    return when (this) {

        ReviewCategory.PERFORMANCE -> {
            Icons.Default.Speed
        }

        ReviewCategory.CODE_QUALITY -> {
            Icons.Default.Code
        }

        ReviewCategory.BEST_PRACTICES -> {
            Icons.Default.CheckCircle
        }

        ReviewCategory.UNKNOWN -> {
            Icons.Default.Info
        }
    }
}