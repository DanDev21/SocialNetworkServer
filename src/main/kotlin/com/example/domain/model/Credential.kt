package com.example.domain.model

data class Credential(
    val emailOrUsername: String,
    val password: String,
) {
    init {
        this.emailOrUsername.apply { trim() }
        this.password.apply { trim() }
    }
}