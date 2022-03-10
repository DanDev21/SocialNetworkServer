package com.example.util

import java.io.File

class FileManager private constructor() {

    companion object {

        fun getNewManagerFor(folderPath: String, folderUrl: String) =
            FolderManager(folderPath, folderUrl)
    }

    data class RawFileData(
        val content: List<Byte>,
        val fileName: String,
    )

    class FolderManager(
        private val path: String, private val url: String
    ) {

        private val root = File(path)

        fun save(data: RawFileData) {
            assureFolderExistence()
            saveFileWithGivenProperties(data)
        }

        private fun assureFolderExistence() = root.mkdirs()

        private fun saveFileWithGivenProperties(data: RawFileData) =
            File(path + data.fileName).writeBytes(data.content.toByteArray())

        fun delete(fileName: String) = File(path + fileName).delete()

        fun deleteUsingUrl(fileUrl: String?) = fileUrl?.let {
            File(path + it.drop(url.length)).delete()
        }
    }
}