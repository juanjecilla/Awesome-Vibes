package com.scallop.data.mappers

import com.scallop.data.entitites.AlbumData
import com.scallop.data.entitites.ArtistData
import com.scallop.data.entitites.ItunesApiResponseData
import com.scallop.domain.entities.AlbumEntity
import com.scallop.domain.entities.ArtistEntity
import com.scallop.domain.entities.ItunesApiResponseEntity

class MusicDataEntityMapper constructor() {

    fun mapArtistsToEntity(data: ItunesApiResponseData<ArtistData>) = ItunesApiResponseEntity(
        resultCount = data.resultCount,
        results = mapArtistsToEntity(data.results)
    )

    fun mapArtistsToEntity(results: List<ArtistData>) = results.map { mapToEntity(it) }

    fun mapToEntity(data: ArtistData) = ArtistEntity(
        artistId = data.artistId,
        amgArtistId = data.amgArtistId,
        artistLinkUrl = data.artistLinkUrl,
        artistName = data.artistName,
        artistType = data.artistType,
        primaryGenreId = data.primaryGenreId,
        primaryGenreName = data.primaryGenreName,
        wrapperType = data.wrapperType
    )

    fun mapToEntity(data: ItunesApiResponseData<AlbumData>)= ItunesApiResponseEntity(
        resultCount = data.resultCount,
        results = mapToEntity(data.results)
    )

    fun mapToEntity(results: List<AlbumData>) = results.map { mapToEntity(it) }

    fun mapToEntity(data: AlbumData) = AlbumEntity(
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
}
