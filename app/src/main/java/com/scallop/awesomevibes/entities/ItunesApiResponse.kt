package com.scallop.awesomevibes.entities

import com.squareup.moshi.Json

data class ItunesApiResponse<T>(
    @Json(name = "resultCount") val resultCount: Long,
    @Json(name = "results") val results: List<T>
)
