package com.deepak.codeauditai.ai.parser

import com.deepak.codeauditai.models.ReviewCategory
import com.deepak.codeauditai.models.ReviewItem

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
                        title = currentCategory.name,
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