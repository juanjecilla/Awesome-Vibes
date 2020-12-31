package com.scallop.awesomevibes.mappers

import com.scallop.awesomevibes.entities.Song
import com.scallop.domain.entities.SongEntity

class SongsMapper {
    fun mapSongs(songs: List<SongEntity>) = songs.map { mapSong(it) }

    private fun mapSong(entity: SongEntity) = Song(
        artistId = entity.artistId,
        artistName = entity.artistName,
        artistViewUrl = entity.artistViewUrl,
        artworkUrl100 = entity.artworkUrl100,
        artworkUrl30 = entity.artworkUrl30,
        artworkUrl60 = entity.artworkUrl60,
        collectionCensoredName = entity.collectionCensoredName,
        collectionExplicitness = entity.collectionExplicitness,
        collectionId = entity.collectionId,
        collectionName = entity.collectionName,
        collectionPrice = entity.collectionPrice,
        collectionViewUrl = entity.collectionViewUrl,
        country = entity.country,
        currency = entity.currency,
        discCount = entity.discCount,
        discNumber = entity.discNumber,
        isStreamable = entity.isStreamable,
        kind = entity.kind,
        previewUrl = entity.previewUrl,
        primaryGenreName = entity.primaryGenreName,
        releaseDate = entity.releaseDate,
        trackCensoredName = entity.trackCensoredName,
        trackCount = entity.trackCount,
        trackExplicitness = entity.trackExplicitness,
        trackId = entity.trackId,
        trackName = entity.trackName,
        trackNumber = entity.trackNumber,
        trackPrice = entity.trackPrice,
        trackTimeMillis = entity.trackTimeMillis,
        trackViewUrl = entity.trackViewUrl,
        wrapperType = entity.wrapperType
    )
}
