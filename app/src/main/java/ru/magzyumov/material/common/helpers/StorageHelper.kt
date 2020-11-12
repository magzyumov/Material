package ru.magzyumov.material.common.helpers

import android.app.Application
import android.net.Uri
import android.os.Environment
import ru.magzyumov.material.common.Constants.StringPattern.Companion.TIMESTAMP_PATTERN
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class StorageHelper @Inject constructor(
        private val application: Application
) {
    private var currentPhotoPath: String = ""

    fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault()).format(Date())
        val storageDir: File? = application.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    fun getPhotoList(): List<String>{
        val result: MutableList<String> = ArrayList()
        val storageDir: File? = application.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        storageDir?.let {dir ->
            dir.listFiles()?.let {list ->
                list.forEach {fileName ->
                    fileName?.let {
                        val fileURI: String = Uri.fromFile(it).toString()
                        result.add(fileURI)
                    }
                }
            }
        }
        return result
    }


    fun getCurrentPhotoPath(): String {
        return currentPhotoPath
    }

}