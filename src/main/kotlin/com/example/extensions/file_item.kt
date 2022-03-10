package com.example.extensions

import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.example.util.FileManager
import io.ktor.http.content.*
import java.util.*

fun PartData.FileItem.getRawFileData() =
    FileManager.RawFileData(bytes, fileName)

private val PartData.FileItem.bytes: List<Byte>
    get() = streamProvider().readBytes().toList()

private val PartData.FileItem.fileName: String
    get() = "${UUID.randomUUID()}.$fileExtension"

private val PartData.FileItem.fileExtension: String
    get() = originalFileName?.takeLastWhile { it != '.' }
        ?: throw InvalidException(Validation.FILE_EXTENSION)
