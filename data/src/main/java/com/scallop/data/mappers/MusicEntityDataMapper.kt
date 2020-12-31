package com.scallop.data.mappers

import com.scallop.data.entitites.*
import com.scallop.domain.entities.*


class MusicEntityDataMapper constructor() {

    fun mapArtistsToData(entity: ItunesApiResponseData<ArtistEntity>) = ItunesApiResponseEntity(
        resultCount = entity.resultCount,
        results = mapArtistsToData(entity.results)
    )

    fun mapArtistsToData(results: List<ArtistEntity>) = results.map { mapToData(it) }

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

    fun mapToData(entity: ItunesApiResponseData<AlbumEntity>)= ItunesApiResponseEntity(
        resultCount = entity.resultCount,
        results = mapToData(entity.results)
    )

    fun mapToData(results: List<AlbumEntity>) = results.map { mapToData(it) }

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
}