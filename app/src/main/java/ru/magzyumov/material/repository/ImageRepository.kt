package ru.magzyumov.material.repository


import android.net.Uri
import ru.magzyumov.material.common.helpers.StorageHelper
import ru.magzyumov.material.data.database.ImageDao
import ru.magzyumov.material.data.entity.FavouritesEntity
import ru.magzyumov.material.data.entity.ImageEntity
import java.io.File
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val storageHelper: StorageHelper,
    private val imageDao: ImageDao
) {

    fun getFileList(): List<String> {
        return storageHelper.getPhotoList()
    }

    fun updateImageDatabase(){
        getFileList().forEach {
            imageDao.insertImage(ImageEntity(it))
        }
    }

    fun deleteImageFile(image: String): Boolean {
        imageDao.deleteImageById(image)
        return storageHelper.deleteImageFile(image)
    }

    suspend fun createImageFile(): File? {
        return storageHelper.createImageFile()
    }

    suspend fun getUriFromFile(file: File): Uri {
        return storageHelper.getUriFromFile(file)
    }

    fun insertImage(image: ImageEntity): Boolean{
        return imageDao.insertImage(image) > 0
    }

    fun getAllImages(): List<ImageEntity>{
        return imageDao.getAllImages()
    }

    fun deleteImage(path: String): Boolean{
        return imageDao.deleteImageById(path) > 0
    }





    fun insertFavourite(favorite: FavouritesEntity): Boolean{
        return imageDao.insertFavourite(favorite) > 0
    }

    fun deleteFavorite(path: String): Boolean{
        return imageDao.deleteFavouriteById(path) > 0
    }

    fun getFavouriteImages(): List<FavouritesEntity>{
        return imageDao.getFavouriteImages()
    }
}