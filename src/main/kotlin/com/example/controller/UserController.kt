package com.example.controller

import com.example.domain.model.User

interface UserController {

    suspend fun add(
        email: String,
        username: String,
        password: String
    )

    suspend fun getById(id: String): User?
}