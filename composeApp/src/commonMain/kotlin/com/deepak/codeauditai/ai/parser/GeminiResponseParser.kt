package com.deepak.codeauditai.ai.parser

import com.deepak.codeauditai.models.ReviewCategory
import com.deepak.codeauditai.models.ReviewItem

/**
 * Parses raw AI-generated Gemini response text into
 * structured [ReviewItem] models that can be rendered
 * inside the UI layer.
 *
 * The parser identifies review sections such as:
 * - CODE_QUALITY
 * - PERFORMANCE
 * - BEST_PRACTICES
 *
 * and converts each bullet-point issue into an
 * individual [ReviewItem].
 *
 * This transformation helps separate:
 * - AI response formatting
 * - domain models
 * - UI rendering concerns
 *
 * The parser is intentionally isolated as a dedicated
 * component to keep repository logic clean and scalable.
 */
class GeminiResponseParser {

    fun parse(response: String): List<ReviewItem> {
        val reviewItems = mutableListOf<ReviewItem>()
        var currentCategory = ReviewCategory.UNKNOWN

        response.lines().forEach { line ->

            val category = ReviewCategory.fromLine(line)

            if (category != ReviewCategory.UNKNOWN) {
                currentCategory = category
            }

            if (line.startsWith("-")) {
                val description = line.removePrefix("-").trim()

                reviewItems.add(
                    ReviewItem(
                        title = currentCategory.name.replace("_", " "),
                        description = description,
                        severity = currentCategory.severity,
                        category = currentCategory
                    )
                )
            }
        }

        return reviewItems
    }
}