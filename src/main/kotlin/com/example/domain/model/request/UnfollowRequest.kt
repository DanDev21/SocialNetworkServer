package com.example.domain.model.request

data class UnfollowRequest(
    val byWhoId: String,
    val otherId: String
)
