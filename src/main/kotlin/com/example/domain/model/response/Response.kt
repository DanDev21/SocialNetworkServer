package com.example.domain.model.response

data class Response(
    val isSuccessful: Boolean,
    val message: String? = null,
)
