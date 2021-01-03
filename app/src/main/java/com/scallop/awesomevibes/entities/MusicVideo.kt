package com.scallop.awesomevibes.entities

data class MusicVideo(
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl30: String,
    val artworkUrl60: String,
    val collectionExplicitness: String,
    val collectionPrice: Double,
    val country: String,
    val currency: String,
    val kind: String,
    val previewUrl: String,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCensoredName: String,
    val trackExplicitness: String,
    val trackId: Long,
    val trackName: String,
    val trackPrice: Double,
    val trackTimeMillis: Int,
    val trackViewUrl: String,
    val wrapperType: String
)