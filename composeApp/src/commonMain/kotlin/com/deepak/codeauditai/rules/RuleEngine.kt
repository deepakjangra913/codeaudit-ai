package com.deepak.codeauditai.rules

import com.deepak.codeauditai.models.RuleViolation
import com.deepak.codeauditai.models.Severity

class RuleEngine {

    fun analyze(code: String): List<RuleViolation> {
        val issues = mutableListOf<RuleViolation>()

        if (code.lines().size > 50) {
            issues.add(
                RuleViolation(
                    message = "Function is too long",
                    severity = Severity.WARNING
                )
            )
        }

        if (code.contains("var")) {
            issues.add(
                RuleViolation(
                    message = "Avoid unnecessary mutability (use val)",
                    severity = Severity.SUGGESTION
                )
            )
        }

        return issues
    }
}