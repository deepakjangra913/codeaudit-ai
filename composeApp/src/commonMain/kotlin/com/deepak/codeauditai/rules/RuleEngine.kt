package com.deepak.codeauditai.rules

class RuleEngine {

    fun analyse(code: String): List<String> {
        val issues = mutableListOf<String>()

        if (code.lines().size > 50) {
            issues.add("Function is too long")
        }

        return issues
    }
}