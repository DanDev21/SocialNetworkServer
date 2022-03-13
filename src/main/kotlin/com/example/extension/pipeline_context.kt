package com.example.extension

import com.example.util.AppException
import com.example.util.AppException.InvalidException.Validation
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.util.pipeline.*

suspend fun PipelineContext<Unit, ApplicationCall>.safe(
    block: suspend PipelineContext<Unit, ApplicationCall>.() -> Unit
) {
    try {
        this.block()
    } catch (e: AppException) {
        e.printStackTrace()
        call.respond(HttpStatusCode(418, e.message))
    } catch (e: NullPointerException) {
        e.printStackTrace()
        call.respond(HttpStatusCode.BadRequest, Validation.REQUEST)
    } catch (e: Exception) {
        e.printStackTrace()
        call.respond(HttpStatusCode.InternalServerError)
    }
}