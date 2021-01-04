package com.scallop.data.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

@Entity(tableName = "artists")
data class ArtistData(
    @PrimaryKey val artistId: Int,
    val amgArtistId: Int?,
    val artistLinkUrl: String,
    val artistName: String,
    val artistType: String,
    val primaryGenreId: Int?,
    val primaryGenreName: String?,
    val wrapperType: String
)