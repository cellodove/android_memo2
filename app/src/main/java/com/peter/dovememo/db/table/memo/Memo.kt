package com.peter.dovememo.db.table.memo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class Memo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,

    @ColumnInfo(name = "title")
    val title : String = "",

    @ColumnInfo(name = "content")
    val content: String = "",

    @ColumnInfo(name = "date")
    val date: String = "",

    @ColumnInfo(name = "image_url")
    val imageUrl: String = ""

)