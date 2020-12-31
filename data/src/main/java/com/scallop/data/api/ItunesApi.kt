package com.scallop.data.api

import com.scallop.data.entitites.AlbumData
import com.scallop.data.entitites.ArtistData
import com.scallop.data.entitites.ItunesApiResponseData
import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ItunesApi {

    @GET("/search?entity=musicArtist")
    @Headers("Accept: application/json")
    suspend fun getArtistsByName(@Query("term") name: String): Response<ItunesApiResponseData<ArtistData>>

    @GET("/search?entity=album")
    fun getAlbumsFromArtist(@Query("term") name: String): Flow<ItunesApiResponseData<AlbumData>>
}