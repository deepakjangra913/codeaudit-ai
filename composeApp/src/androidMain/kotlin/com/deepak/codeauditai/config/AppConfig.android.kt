package com.deepak.codeauditai.config

import com.deepak.codeauditai.BuildConfig

actual object AppConfig {
    actual val geminiApiKey: String
        get() = BuildConfig.GEMINI_API_KEY
}