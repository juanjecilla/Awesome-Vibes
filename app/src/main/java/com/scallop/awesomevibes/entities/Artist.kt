package com.scallop.awesomevibes.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
data class Artist(
    @PrimaryKey val artistId: Int,
    val amgArtistId: Int?,
    val artistLinkUrl: String,
    val artistName: String,
    val artistType: String,
    val primaryGenreId: Int,
    val primaryGenreName: String,
    val wrapperType: String
)