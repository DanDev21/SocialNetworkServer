package com.example.data.dto.util

import com.example.core.util.Url
import com.example.core.util.extensions.bytes
import com.example.core.util.extensions.fileExtension
import io.ktor.http.content.*
import java.util.*

sealed class Picture private constructor(
    open val name: String,
    open val data: ByteArray,
    open val url: String,
) {
    class ProfilePicture(
        override val name: String,
        override val data: ByteArray
    ) : Picture(
        name = name,
        data = data,
        url = "${Url.PROFILE_PICTURE}${name}"
    )

    companion object {

        fun from(item: PartData.FileItem) = ProfilePicture(
            name = "${UUID.randomUUID()}.${item.fileExtension}",
            data = item.bytes
        )
    }
}
