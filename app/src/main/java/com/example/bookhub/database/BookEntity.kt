package com.example.bookhub.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val book_id: Int,
    @ColumnInfo(name = "book_name") val name: String,
    @ColumnInfo(name = "book_author") val author: String,
    @ColumnInfo(name = "book_price") val price: String,
    @ColumnInfo(name = "book_rating") val rating: String,
    @ColumnInfo(name = "book_desc") val bookDesc: String,
    @ColumnInfo(name = "book_image") val image: String
){
}