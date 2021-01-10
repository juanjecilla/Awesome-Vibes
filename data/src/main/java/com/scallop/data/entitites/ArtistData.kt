package com.scallop.data.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "artists")
data class ArtistData(
     @Json(name = "artistId") @PrimaryKey val artistId: Int,
     @Json(name = "amgArtistId") val amgArtistId: Int?,
     @Json(name = "artistLinkUrl") val artistLinkUrl: String,
     @Json(name = "artistName") val artistName: String,
     @Json(name = "artistType") val artistType: String,
     @Json(name = "primaryGenreId") val primaryGenreId: Int?,
     @Json(name = "primaryGenreName") val primaryGenreName: String?,
     @Json(name = "wrapperType") val wrapperType: String
)