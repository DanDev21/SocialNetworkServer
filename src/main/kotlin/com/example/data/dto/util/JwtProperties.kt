package com.example.data.dto.util

import com.example.util.JwtProperty
import io.ktor.config.*

data class JwtProperties(
    val issuer: String,
    val audience: String,
    val secret: String,
    val realm: String,
) {

    companion object {

        fun from(config: ApplicationConfig) =
            JwtProperties(
                issuer = config.property(JwtProperty.DOMAIN).getString(),
                audience = config.property(JwtProperty.AUDIENCE).getString(),
                secret = config.property(JwtProperty.SECRET).getString(),
                realm = config.property(JwtProperty.REALM).getString(),
            )
    }
}
