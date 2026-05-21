package com.deepak.codeauditai.models

/**
 * Represents a single rule-based code analysis issue
 * detected by the local static analysis engine.
 *
 * A [RuleViolation] is generated when the rule engine
 * identifies code patterns that may negatively impact:
 * - performance
 * - readability
 * - maintainability
 * - architecture quality
 * - best practices
 *
 * Unlike AI-generated suggestions, rule violations are
 * deterministic and based on predefined analysis rules.
 *
 * @property message Human-readable explanation describing
 * the detected issue.
 *
 * @property severity Indicates the importance level
 * of the detected violation.
 */
data class RuleViolation(
    val message: String,
    val severity: Severity
)