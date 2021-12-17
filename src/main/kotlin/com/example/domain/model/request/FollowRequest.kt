package com.example.domain.model.request

data class FollowRequest(
    val byWhoId: String,
    val otherId: String,
)