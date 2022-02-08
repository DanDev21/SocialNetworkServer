package com.example.domain.data.dto.crud

sealed class CrudResult<T> {
    data class InsertResult<T>(val succeeded: Boolean, val obj: T) : CrudResult<T>()
    data class FindResult<T>(val obj: T?) : CrudResult<T>() {

        val founded: Boolean    get() = obj != null
        val failed: Boolean     get() = !founded

        val item: T get() = obj ?: throw NullPointerException()
    }
    data class DeleteResult<T>(val deleteCount: Long) : CrudResult<T>() {

        fun wasAcknowledged() = deleteCount == 1L
    }
    data class FindManyResult<T>(val items: List<T>) : CrudResult<T>()
}
