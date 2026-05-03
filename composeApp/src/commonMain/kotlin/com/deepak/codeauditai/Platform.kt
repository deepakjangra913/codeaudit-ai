package com.deepak.codeauditai

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform