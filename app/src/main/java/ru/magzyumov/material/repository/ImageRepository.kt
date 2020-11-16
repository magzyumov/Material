package ru.magzyumov.material.repository


import android.net.Uri
import ru.magzyumov.material.common.helpers.StorageHelper
import java.io.File
import javax.inject.Inject

class ImageRepository @Inject constructor(
        private val storageHelper: StorageHelper) {

    fun getFileList(): List<String> {
        return storageHelper.getPhotoList()
    }

    fun deleteImageFile(image: String): Boolean {
        return storageHelper.deleteImageFile(image)
    }

    suspend fun createImageFile(): File? {
        return storageHelper.createImageFile()
    }

    suspend fun getUriFromFile(file: File): Uri {
        return storageHelper.getUriFromFile(file)
    }
}