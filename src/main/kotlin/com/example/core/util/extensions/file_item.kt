package com.example.core.util.extensions

import io.ktor.http.content.*

val PartData.FileItem.bytes: ByteArray
    get() = streamProvider().readBytes()

val PartData.FileItem.fileExtension: String?
    get() = originalFileName?.takeLastWhile { it != '.' }
