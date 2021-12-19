package com.example.domain.data.dto.request

data class UnfollowRequest(
    val byWhoId: String,
    val otherId: String
)
