package com.scallop.awesomevibes.mappers

import com.scallop.awesomevibes.entities.Album
import com.scallop.domain.entities.AlbumEntity

class AlbumsMapper {
    fun mapAlbum(albums: List<AlbumEntity>) = albums.map { mapAlbum(it) }

    private fun mapAlbum(album: AlbumEntity) = Album(
        amgArtistId = album.amgArtistId,
        artistId = album.artistId,
        artistName = album.artistName,
        artistViewUrl = album.artistViewUrl,
        artworkUrl100 = album.artworkUrl100,
        artworkUrl60 = album.artworkUrl60,
        collectionCensoredName = album.collectionCensoredName,
        collectionExplicitness = album.collectionExplicitness,
        collectionId = album.collectionId,
        collectionName = album.collectionName,
        collectionPrice = album.collectionPrice,
        collectionType = album.collectionType,
        collectionViewUrl = album.collectionViewUrl,
        copyright = album.copyright,
        country = album.country,
        currency = album.currency,
        primaryGenreName = album.primaryGenreName,
        releaseDate = album.releaseDate,
        trackCount = album.trackCount,
        wrapperType = album.wrapperType
    )
}
