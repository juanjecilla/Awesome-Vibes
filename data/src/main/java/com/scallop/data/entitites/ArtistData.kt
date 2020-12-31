package com.scallop.data.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

@Entity(tableName = "artists")
data class ArtistData(
     @Json(name = "artistId") @SerializedName("artistId") @PrimaryKey val artistId: Int,
     @Json(name = "amgArtistId") @SerializedName("amgArtistId") val amgArtistId: Int?,
     @Json(name = "artistLinkUrl") @SerializedName("artistLinkUrl") val artistLinkUrl: String,
     @Json(name = "artistName") @SerializedName("artistName") val artistName: String,
     @Json(name = "artistType") @SerializedName("artistType") val artistType: String,
     @Json(name = "primaryGenreId") @SerializedName("primaryGenreId") val primaryGenreId: Int,
     @Json(name = "primaryGenreName") @SerializedName("primaryGenreName") val primaryGenreName: String,
     @Json(name = "wrapperType") @SerializedName("wrapperType") val wrapperType: String
)