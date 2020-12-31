package com.scallop.data.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "movie_detail")
data class MovieDetailData(
    @PrimaryKey @Json(name = "id") var id: Long? = null,
    @Json(name = "title") var title: String? = null,
    @Json(name = "poster_path") var posterPath: String? = null,
    @Json(name = "overview") var overview: String? = null
)
