package com.example.extension

import com.example.util.Length
import com.example.util.QueryParams
import com.example.util.RouteParams
import com.example.util.Token
import com.example.data.dto.util.CrudResult
import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

// authentication

val ApplicationCall.requesterId: String
    get() = principal<JWTPrincipal>()?.requesterId
        ?: throw InvalidException(Validation.TOKEN_ID)

internal val JWTPrincipal.requesterId: String
    get() = getClaim(Token.REQUESTER_ID, String::class)
        ?: throw InvalidException(Validation.TOKEN_ID)

// route parameters

val ApplicationCall.objectId: String
    get() = parameters[RouteParams.ID]
        ?: throw InvalidException(Validation.NULL_ROUTE_PARAM)

val ApplicationCall.pageNumber: Int
    get() = request.queryParameters[QueryParams.PAGE_NUMBER]
        ?.toIntOrNull() ?: 0

val ApplicationCall.pageSize: Int
    get() = request.queryParameters[QueryParams.PAGE_SIZE]
        ?.toIntOrNull() ?: Length.RESOURCE_PAGE_SIZE

// functions

suspend inline fun <reified T : Any> ApplicationCall.receive(): T =
    receiveOrNull() ?: throw InvalidException(Validation.NULL_OR_INVALID_REQUEST)

// respond

suspend fun <T> ApplicationCall.confirm(result: CrudResult<T>) = respond(
    if (result.succeeded) HttpStatusCode.OK else HttpStatusCode.ExpectationFailed
)