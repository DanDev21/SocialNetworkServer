package com.example.data.dto.response

import com.example.data.dto.util.CrudResult.FindResult

data class ListResponse <T> (val items: List<T>) {

    constructor(result: FindResult<List<T>>) : this(result.content)
}
