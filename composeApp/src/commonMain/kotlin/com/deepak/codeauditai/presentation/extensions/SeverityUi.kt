package com.deepak.codeauditai.presentation.extensions

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.deepak.codeauditai.models.Severity

fun Severity.gradientBrush(): Brush {

    return when (this) {

        Severity.CRITICAL -> {
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFF3B0D0D),
                    Color(0xFF5C1A1A)
                )
            )
        }

        Severity.WARNING -> {
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFF3D2B0B),
                    Color(0xFF5A3D0C)
                )
            )
        }

        Severity.INFO -> {
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFF0B223D),
                    Color(0xFF123A63)
                )
            )
        }

        Severity.SUGGESTION -> {
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFF0B3D2E),
                    Color(0xFF145A43)
                )
            )
        }
    }
}