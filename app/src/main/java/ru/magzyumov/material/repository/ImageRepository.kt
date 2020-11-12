package ru.magzyumov.material.repository


import ru.magzyumov.material.common.helpers.StorageHelper
import javax.inject.Inject

class ImageRepository @Inject constructor(
        private val storageHelper: StorageHelper) {

    fun getFileList(): List<String> {
        return storageHelper.getPhotoList()
    }

}