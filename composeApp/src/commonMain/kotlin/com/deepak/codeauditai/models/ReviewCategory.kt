package com.deepak.codeauditai.models

enum class ReviewCategory(
    val section: String,
    val severity: Severity
) {

    CODE_QUALITY(
        section = "CODE_QUALITY",
        severity = Severity.SUGGESTION
    ),

    PERFORMANCE(
        section = "PERFORMANCE",
        severity = Severity.WARNING
    ),

    BEST_PRACTICES(
        section = "BEST_PRACTICES",
        severity = Severity.INFO
    ),

    UNKNOWN(
        section = "UNKNOWN",
        severity = Severity.INFO
    );

    companion object {

        fun fromLine(line: String): ReviewCategory {

            return entries.firstOrNull {
                line.startsWith(it.section)
            } ?: UNKNOWN
        }
    }
}