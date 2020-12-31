package com.scallop.data.entitites

import com.squareup.moshi.Json

data class ItunesApiResponseData<T>(
    @Json(name = "resultCount") val resultCount: Long,
    @Json(name = "results") val results: List<T>
)
