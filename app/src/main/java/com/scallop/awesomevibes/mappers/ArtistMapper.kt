package com.scallop.awesomevibes.mappers

import com.scallop.awesomevibes.entities.Artist
import com.scallop.domain.entities.ArtistEntity

class ArtistMapper {

    fun mapArtist(artists: List<ArtistEntity>?) = artists?.map { mapArtist(it) }

    private fun mapArtist(artist: ArtistEntity) = Artist(
        artistId = artist.artistId,
        amgArtistId = artist.amgArtistId,
        artistLinkUrl = artist.artistLinkUrl,
        artistName = artist.artistName,
        artistType = artist.artistType,
        primaryGenreId = artist.primaryGenreId,
        primaryGenreName = artist.primaryGenreName,
        wrapperType = artist.wrapperType
    )
}
