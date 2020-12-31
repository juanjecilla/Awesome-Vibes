package com.scallop.data.api

import com.scallop.data.commons.Properties
import com.scallop.data.entitites.AlbumData
import com.scallop.data.entitites.ArtistData
import com.scallop.data.entitites.ItunesApiResponseData
import com.scallop.data.entitites.SongData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ItunesApi {

    @GET("/search?entity=musicArtist")
    @Headers("Accept: application/json")
    suspend fun getArtistsByName(
        @Query("term") name: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Properties.ITEMS_PER_PAGE
    ): Response<ItunesApiResponseData<ArtistData>>

    @GET("/search?entity=album")
    @Headers("Accept: application/json")
    suspend fun getAlbumsFromArtist(
        @Query("term") name: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Properties.ITEMS_PER_PAGE
    ): Response<ItunesApiResponseData<AlbumData>>

    @GET("/search?entity=song&attribute=albumTerm")
    @Headers("Accept: application/json")
    suspend fun getSongsFromAlbum(
        @Query("term") name: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Properties.ITEMS_PER_PAGE
    ): Response<ItunesApiResponseData<SongData>>
}