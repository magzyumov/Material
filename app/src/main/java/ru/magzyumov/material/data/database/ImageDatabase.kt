package ru.magzyumov.material.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.magzyumov.material.data.entity.FavouritesEntity
import ru.magzyumov.material.data.entity.ImageEntity


@Database(entities = [ImageEntity::class, FavouritesEntity::class], version = 1, exportSchema = false)
abstract class ImageDatabase: RoomDatabase() {
    abstract fun imageDao(): ImageDao
}