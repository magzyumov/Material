package ru.magzyumov.material.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
class FavouritesEntity(

    @PrimaryKey
    @ColumnInfo(name = "path")
    var path: String

    ) {

    class FavouritesCount(var count: Int)
}
