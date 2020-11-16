package ru.magzyumov.material.common.helpers

import android.app.Application
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import ru.magzyumov.material.BuildConfig
import ru.magzyumov.material.common.Constants.StringPattern.Companion.TIMESTAMP_PATTERN
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class StorageHelper @Inject constructor(
        private val application: Application
) {
    private var currentPhotoPath: String = ""

    fun createImageFile(): File? {
        val timeStamp: String = SimpleDateFormat(TIMESTAMP_PATTERN, Locale.getDefault()).format(Date())
        val storageDir: File? = application.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var result: File? = null
        try {
            result = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            ).apply {
                currentPhotoPath = absolutePath
            }
            return result
        } catch (error: IOException) {
            Log.e("ERROR", error.localizedMessage)
        }
        return result
    }

    fun getUriFromFile(fileName: File): Uri {
        return FileProvider.getUriForFile(
            application,
            BuildConfig.APPLICATION_ID + ".fileprovider",
            fileName
        )
    }

    fun deleteImageFile(fileName: String): Boolean {
        val url = Uri.parse(fileName).path
        val file: File = File(url)
        return file.delete()
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