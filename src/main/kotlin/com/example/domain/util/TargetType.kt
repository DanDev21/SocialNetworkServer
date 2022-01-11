package com.example.domain.util

sealed class TargetType(val int: Int) {

    companion object {

        fun fromInt(int: Int) =
            when(int) {
                1 -> Comment
                2 -> Post
                else -> Default
            }
    }

    object Default : TargetType(0)
    object Comment : TargetType(1)
    object Post : TargetType(2)
}