package ru.magzyumov.material.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.magzyumov.material.data.entity.FavouritesEntity


@Database(entities = [FavouritesEntity::class], version = 2, exportSchema = false)
abstract class ImageDatabase: RoomDatabase() {
    abstract fun imageDao(): ImageDao
}