package com.example.util

import com.example.util.FileManager.RawFileData
import com.example.extension.getRawFileData
import com.example.data.dto.util.Tuple
import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.request.*

class FileInterceptor private constructor(val parts: List<PartData>) {

    companion object {

        suspend fun from(call: ApplicationCall) =
            FileInterceptor(call.receiveMultipart().readAllParts())
    }

    inline fun <reified T> extractDataAndFile() =
        Tuple(extract<T>(), extractImageFileData())

    inline fun <reified T> extract(): T = parts
        .filterIsInstance<PartData.FormItem>()
        .firstOrNull { it.name == QueryParams.RAW_DATA }
        ?.let { Gson().fromJson(it.value, T::class.java) }
        ?: throw InvalidException(Validation.REQUEST)


    fun extractImageFileData(): RawFileData = parts
        .filterIsInstance<PartData.FileItem>()
        .firstOrNull()
        ?.getRawFileData()
        ?.takeIf { Length.Min.IMAGE_FILE_SIZE <= it.content.size }
        ?: throw InvalidException(Validation.IMAGE_FILE_SIZE)
}