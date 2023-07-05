package com.practice.o_ai.models.request

data class ImageGenerateRequest(
    val n: Int,
    val prompt: String,
    val size: String
)