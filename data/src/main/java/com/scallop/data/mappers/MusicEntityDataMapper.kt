package com.scallop.data.mappers

import com.scallop.data.entitites.AlbumData
import com.scallop.data.entitites.ArtistData
import com.scallop.data.entitites.ItunesApiResponseData
import com.scallop.data.entitites.SongData
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity
import com.scallop.domain.entities.SongEntity


class MusicEntityDataMapper {

    fun mapArtistsToData(entity: ItunesApiResponseData<ArtistEntity>) = ItunesApiResponseEntity(
        resultCount = entity.resultCount,
        results = mapArtistsToData(entity.results)
    )

    fun mapArtistsToData(entities: List<ArtistEntity>) = entities.map { mapToData(it) }

    fun mapToData(entity: ArtistEntity) = ArtistData(
        artistId = entity.artistId,
        amgArtistId = entity.amgArtistId,
        artistLinkUrl = entity.artistLinkUrl,
        artistName = entity.artistName,
        artistType = entity.artistType,
        primaryGenreId = entity.primaryGenreId,
        primaryGenreName = entity.primaryGenreName,
        wrapperType = entity.wrapperType
    )

    fun mapToData(entity: ItunesApiResponseData<AlbumEntity>) = ItunesApiResponseEntity(
        resultCount = entity.resultCount,
        results = mapToData(entity.results)
    )

    fun mapToData(entities: List<AlbumEntity>) = entities.map { mapToData(it) }

    fun mapToData(entity: AlbumEntity) = AlbumData(
        amgArtistId = entity.amgArtistId,
        artistId = entity.artistId,
        artistName = entity.artistName,
        artistViewUrl = entity.artistViewUrl,
        artworkUrl100 = entity.artworkUrl100,
        artworkUrl60 = entity.artworkUrl60,
        collectionCensoredName = entity.collectionCensoredName,
        collectionExplicitness = entity.collectionExplicitness,
        collectionId = entity.collectionId,
        collectionName = entity.collectionName,
        collectionPrice = entity.collectionPrice,
        collectionType = entity.collectionType,
        collectionViewUrl = entity.collectionViewUrl,
        copyright = entity.copyright,
        country = entity.country,
        currency = entity.currency,
        primaryGenreName = entity.primaryGenreName,
        releaseDate = entity.releaseDate,
        trackCount = entity.trackCount,
        wrapperType = entity.wrapperType
    )

    fun mapSongToData(entities: List<SongEntity>) = entities.map { mapSongToData(it) }

    fun mapSongToData(entity: SongEntity) = SongData(
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