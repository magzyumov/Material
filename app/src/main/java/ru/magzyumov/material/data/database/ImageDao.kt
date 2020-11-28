package ru.magzyumov.material.data.database

import androidx.room.*
import ru.magzyumov.material.data.entity.FavouritesEntity
import ru.magzyumov.material.data.entity.ImageEntity


@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(favourite: FavouritesEntity): Long

    @Query("SELECT * FROM favourites")
    fun getFavouriteImages(): List<FavouritesEntity>

    @Query("DELETE FROM favourites WHERE path= :path")
    fun deleteFavouriteById(path: String): Int



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(image: ImageEntity): Long


    @Query("SELECT * FROM images")
    fun getAllImages(): List<ImageEntity>

    @Query("DELETE FROM images WHERE path= :path")
    fun deleteImageById(path: String): Int
}
