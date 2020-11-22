package ru.magzyumov.material.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
class ImageEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "path")
    var path: String,

    @ColumnInfo(name = "liked")
    var liked: Boolean

    ) {

}
