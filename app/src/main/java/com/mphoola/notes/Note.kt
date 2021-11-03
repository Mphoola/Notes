package com.mphoola.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notesTable")
data class Note(@ColumnInfo(name = "title") val noteTitle:String,
                @ColumnInfo(name = "description") val noteDescription: String,
                @ColumnInfo(name = "trashed", defaultValue = "0") val trashed: String,
                @ColumnInfo(name = "favourite", defaultValue = "0") val favourite: String,
                @ColumnInfo(name = "bg_color", defaultValue = "#296D98") val color: String,
                @ColumnInfo(name = "timestamp") val noteTimeStamp: String) {
    @PrimaryKey(autoGenerate = true) var id = 0
}