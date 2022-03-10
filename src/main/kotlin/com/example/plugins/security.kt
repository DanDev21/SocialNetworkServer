package com.example.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.dto.util.JwtProperties
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

fun Application.configureSecurity() {

    authentication {
        jwt {
            val jwtProperties = JwtProperties.from(environment.config)

            realm = jwtProperties.realm

            verifier(JWT
                .require(Algorithm.HMAC256(jwtProperties.secret))
                .withAudience(jwtProperties.audience)
                .withIssuer(jwtProperties.issuer)
                .build()
            )

            validate { credential ->
                if (credential.payload.audience.contains(jwtProperties.audience)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }

}
