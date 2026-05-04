package com.deepak.codeauditai.models

data class RuleViolation(
    val message: String,
    val severity: Severity
)