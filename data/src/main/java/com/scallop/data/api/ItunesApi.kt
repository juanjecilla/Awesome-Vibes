package com.scallop.data.api

import com.scallop.data.commons.Properties
import com.scallop.data.entitites.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ItunesApi {

    @GET("/search?entity=musicArtist")
    suspend fun getArtistsByName(
        @Query("term") name: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Properties.ITEMS_PER_PAGE
    ): ItunesApiResponseData<ArtistData>

    @GET("/search?entity=album")
    suspend fun getAlbumsFromArtist(
        @Query("term") name: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Properties.ITEMS_PER_PAGE
    ): ItunesApiResponseData<AlbumData>

    @GET("/search?entity=song&attribute=albumTerm")
    suspend fun getSongsFromAlbum(
        @Query("term") name: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Properties.ITEMS_PER_PAGE
    ): ItunesApiResponseData<SongData>

    @GET("/search?entity=musicVideo&attribute=songTerm")
    suspend fun getMusicVideo(
        @Query("term") name: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Properties.ITEMS_PER_PAGE
    ): ItunesApiResponseData<MusicVideoData>
}