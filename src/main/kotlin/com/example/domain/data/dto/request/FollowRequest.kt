package com.example.domain.data.dto.request

data class FollowRequest(
    val byWhoId: String,
    val otherId: String,
)