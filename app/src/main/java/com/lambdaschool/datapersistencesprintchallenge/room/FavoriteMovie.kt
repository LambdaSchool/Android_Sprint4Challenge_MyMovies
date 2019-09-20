package com.lambdaschool.datapersistencesprintchallenge.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoriteMovie (
    val movieTitle: String,
    val movieRating: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int
)