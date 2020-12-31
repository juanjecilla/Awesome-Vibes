package com.scallop.data.entitites

import com.squareup.moshi.Json


data class MovieItemSourcesData(
    @Json(name = "page") var page: Int?,
    @Json(name = "total_results") var total_results: Int?,
    @Json(name = "total_pages") var total_pages: Int?,
    @Json(name = "results") var results: List<MovieItemData> = emptyList()
)
