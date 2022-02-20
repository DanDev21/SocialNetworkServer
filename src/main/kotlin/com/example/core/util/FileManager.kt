package com.example.core.util

import com.example.data.dto.util.Picture
import java.io.File

object FileManager {

    fun save(
        picture: Picture,
        folderPath: String,
    ) {
        assureFolderExistence(folderPath)
        save(
            filePath = "$folderPath${picture.name}",
            fileContent = picture.data
        )
    }

    private fun assureFolderExistence(folderPath: String) {
        File(folderPath).mkdirs()
    }

    private fun save(filePath: String, fileContent: ByteArray) =
        File(filePath).writeBytes(fileContent)

    fun delete(filePath: String) = File(filePath).delete()
}