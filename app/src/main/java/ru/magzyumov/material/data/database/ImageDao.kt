package ru.magzyumov.material.data.database

import androidx.room.*
import ru.magzyumov.material.data.entity.FavouritesEntity


@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(favourite: FavouritesEntity): Long

    @Query("SELECT * FROM favourites")
    fun getFavouriteImages(): List<FavouritesEntity>

    @Query("DELETE FROM favourites WHERE path= :path")
    fun deleteFavouriteById(path: String): Int
}
