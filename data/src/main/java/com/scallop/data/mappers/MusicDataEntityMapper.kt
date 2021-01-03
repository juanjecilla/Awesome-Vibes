package com.scallop.data.mappers

import com.scallop.data.entitites.*
import com.scallop.domain.entities.*

class MusicDataEntityMapper constructor() {

    fun mapArtistsToEntity(data: ItunesApiResponseData<ArtistData>) = ItunesApiResponseEntity(
        resultCount = data.resultCount,
        results = mapArtistsToEntity(data.results)
    )

    private fun mapArtistsToEntity(results: List<ArtistData>) = results.map { mapToEntity(it) }

    private fun mapToEntity(data: ArtistData) = ArtistEntity(
        artistId = data.artistId,
        amgArtistId = data.amgArtistId,
        artistLinkUrl = data.artistLinkUrl,
        artistName = data.artistName,
        artistType = data.artistType,
        primaryGenreId = data.primaryGenreId,
        primaryGenreName = data.primaryGenreName,
        wrapperType = data.wrapperType
    )

    fun mapToEntity(data: ItunesApiResponseData<AlbumData>) = ItunesApiResponseEntity(
        resultCount = data.resultCount,
        results = mapToEntity(data.results)
    )

    private fun mapToEntity(results: List<AlbumData>) = results.map { mapToEntity(it) }

    private fun mapToEntity(data: AlbumData) = AlbumEntity(
        amgArtistId = data.amgArtistId,
        artistId = data.artistId,
        artistName = data.artistName,
        artistViewUrl = data.artistViewUrl,
        artworkUrl100 = data.artworkUrl100,
        artworkUrl60 = data.artworkUrl60,
        collectionCensoredName = data.collectionCensoredName,
        collectionExplicitness = data.collectionExplicitness,
        collectionId = data.collectionId,
        collectionName = data.collectionName,
        collectionPrice = data.collectionPrice,
        collectionType = data.collectionType,
        collectionViewUrl = data.collectionViewUrl,
        copyright = data.copyright,
        country = data.country,
        currency = data.currency,
        primaryGenreName = data.primaryGenreName,
        releaseDate = data.releaseDate,
        trackCount = data.trackCount,
        wrapperType = data.wrapperType
    )

    fun mapSongToEntity(results: List<SongData>) = results.map { mapSongToEntity(it) }

    private fun mapSongToEntity(data: SongData) = SongEntity(
        artistId = data.artistId,
        artistName = data.artistName,
        artistViewUrl = data.artistViewUrl,
        artworkUrl100 = data.artworkUrl100,
        artworkUrl30 = data.artworkUrl30,
        artworkUrl60 = data.artworkUrl60,
        collectionCensoredName = data.collectionCensoredName,
        collectionExplicitness = data.collectionExplicitness,
        collectionId = data.collectionId,
        collectionName = data.collectionName,
        collectionPrice = data.collectionPrice,
        collectionViewUrl = data.collectionViewUrl,
        country = data.country,
        currency = data.currency,
        discCount = data.discCount,
        discNumber = data.discNumber,
        isStreamable = data.isStreamable,
        kind = data.kind,
        previewUrl = data.previewUrl,
        primaryGenreName = data.primaryGenreName,
        releaseDate = data.releaseDate,
        trackCensoredName = data.trackCensoredName,
        trackCount = data.trackCount,
        trackExplicitness = data.trackExplicitness,
        trackId = data.trackId,
        trackName = data.trackName,
        trackNumber = data.trackNumber,
        trackPrice = data.trackPrice,
        trackTimeMillis = data.trackTimeMillis,
        trackViewUrl = data.trackViewUrl,
        wrapperType = data.wrapperType
    )

    fun mapMusicVideoToEntity(data: MusicVideoData) = MusicVideoEntity(
        artistId = data.artistId,
        artistName = data.artistName,
        artistViewUrl = data.artistViewUrl,
        artworkUrl100 = data.artworkUrl100,
        artworkUrl30 = data.artworkUrl30,
        artworkUrl60 = data.artworkUrl60,
        collectionExplicitness = data.collectionExplicitness,
        collectionPrice = data.collectionPrice,
        country = data.country,
        currency = data.currency,
        kind = data.kind,
        previewUrl = data.previewUrl,
        primaryGenreName = data.primaryGenreName,
        releaseDate = data.releaseDate,
        trackCensoredName = data.trackCensoredName,
        trackExplicitness = data.trackExplicitness,
        trackId = data.trackId,
        trackName = data.trackName,
        trackPrice = data.trackPrice,
        trackTimeMillis = data.trackTimeMillis,
        trackViewUrl = data.trackViewUrl,
        wrapperType = data.wrapperType
    )
}
