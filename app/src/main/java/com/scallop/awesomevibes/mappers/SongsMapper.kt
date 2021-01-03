package com.scallop.awesomevibes.mappers

import com.scallop.awesomevibes.entities.MusicVideo
import com.scallop.awesomevibes.entities.Song
import com.scallop.domain.entities.MusicVideoEntity
import com.scallop.domain.entities.SongEntity

class SongsMapper {
    fun mapSongs(songs: List<SongEntity>) = songs.map { mapSong(it) }

    fun mapSong(entity: SongEntity) = Song(
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
        wrapperType = entity.wrapperType,
        savedSong = entity.savedSong
    )

    fun mapSong(song: Song) = SongEntity(
        artistId = song.artistId,
        artistName = song.artistName,
        artistViewUrl = song.artistViewUrl,
        artworkUrl100 = song.artworkUrl100,
        artworkUrl30 = song.artworkUrl30,
        artworkUrl60 = song.artworkUrl60,
        collectionCensoredName = song.collectionCensoredName,
        collectionExplicitness = song.collectionExplicitness,
        collectionId = song.collectionId,
        collectionName = song.collectionName,
        collectionPrice = song.collectionPrice,
        collectionViewUrl = song.collectionViewUrl,
        country = song.country,
        currency = song.currency,
        discCount = song.discCount,
        discNumber = song.discNumber,
        isStreamable = song.isStreamable,
        kind = song.kind,
        previewUrl = song.previewUrl,
        primaryGenreName = song.primaryGenreName,
        releaseDate = song.releaseDate,
        trackCensoredName = song.trackCensoredName,
        trackCount = song.trackCount,
        trackExplicitness = song.trackExplicitness,
        trackId = song.trackId,
        trackName = song.trackName,
        trackNumber = song.trackNumber,
        trackPrice = song.trackPrice,
        trackTimeMillis = song.trackTimeMillis,
        trackViewUrl = song.trackViewUrl,
        wrapperType = song.wrapperType
    )


    fun mapMusicVideo(entity: MusicVideoEntity) = MusicVideo(
        artistId = entity.artistId,
        artistName = entity.artistName,
        artistViewUrl = entity.artistViewUrl,
        artworkUrl100 = entity.artworkUrl100,
        artworkUrl30 = entity.artworkUrl30,
        artworkUrl60 = entity.artworkUrl60,
        collectionExplicitness = entity.collectionExplicitness,
        collectionPrice = entity.collectionPrice,
        country = entity.country,
        currency = entity.currency,
        kind = entity.kind,
        previewUrl = entity.previewUrl,
        primaryGenreName = entity.primaryGenreName,
        releaseDate = entity.releaseDate,
        trackCensoredName = entity.trackCensoredName,
        trackExplicitness = entity.trackExplicitness,
        trackId = entity.trackId,
        trackName = entity.trackName,
        trackPrice = entity.trackPrice,
        trackTimeMillis = entity.trackTimeMillis,
        trackViewUrl = entity.trackViewUrl,
        wrapperType = entity.wrapperType,
    )
}
