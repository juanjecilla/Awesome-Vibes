package com.scallop.data.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movie_items")
data class MovieItemData(
    @PrimaryKey @Json(name = "id") var id: Long,
    @Json(name = "title") var title: String? = null,
    @Json(name = "poster_path") var posterPath: String? = null,
    var fav : Boolean = false
)
