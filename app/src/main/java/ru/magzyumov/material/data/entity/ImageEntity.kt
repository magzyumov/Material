package ru.magzyumov.material.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
class ImageEntity(
        @ColumnInfo(name = "path")
        var path: String = ""
    ) {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "liked")
    var liked: Boolean = false
}
