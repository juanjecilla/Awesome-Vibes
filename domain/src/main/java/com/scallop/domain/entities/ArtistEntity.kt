package com.scallop.domain.entities

data class ArtistEntity(
    val artistId: Int,
    val amgArtistId: Int?,
    val artistLinkUrl: String,
    val artistName: String,
    val artistType: String,
    val primaryGenreId: Int?,
    val primaryGenreName: String?,
    val wrapperType: String
)